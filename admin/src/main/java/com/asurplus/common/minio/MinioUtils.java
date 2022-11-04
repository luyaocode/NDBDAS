package com.asurplus.common.minio;

import com.asurplus.common.enums.StatusEnums;
import com.asurplus.common.utils.DateUtils;
import com.asurplus.common.utils.FileUtils;
import com.asurplus.common.utils.RES;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * minio工具类
 *
 * @Author Lizhou
 */
@Slf4j
@Component
public class MinioUtils {

    @Autowired
    private MinioClient client;
    @Autowired
    private MinioProp minioProp;

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    /**
     * 获取全部bucket
     */
    @SneakyThrows
    public List<Bucket> getAllBuckets() {
        return client.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public Optional<Bucket> getBucket(String bucketName) {
        return client.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(bucketName);
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String bucketName, String objectName, Integer expires) {
        return client.presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 获取文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName) {
        return client.getObject(bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        client.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        client.putObject(bucketName, objectName, stream, size, contextType);
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return client.statObject(bucketName, objectName);
    }

    /**
     * 删除文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public RES removeObject(String bucketName, String objectName) {
        try {
            client.removeObject(bucketName, objectName);
            return RES.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RES.no("删除失败");
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return
     */
    public RES uploadFile(MultipartFile file, String dir) {
        // 判断上传文件是否为空
        if (file.isEmpty()) {
            return RES.no("上传文件不能为空");
        }
        try {
            // 判断存储桶是否存在
            createBucket(MinioConst.MINIO_BUCKET);
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 文件后缀 例如：png
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            // 新的文件名
            String fileName = (StringUtils.isNotBlank(dir) ? dir + "/" : "") + DateUtils.getYyyyMMdd() + "/" + uuid + "." + fileSuffix;
            // 开始上传
            client.putObject(MinioConst.MINIO_BUCKET, fileName, file.getInputStream(), file.getContentType());
            return RES.ok(StatusEnums.UPLOAD_SUCCESS.getMsg(), minioProp.getEndpoint() + "/" + MinioConst.MINIO_BUCKET + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RES.no(StatusEnums.UPLOAD_ERROR);
    }

    /**
     * 上传微信头像
     *
     * @param url 网络图片地址
     * @return
     */
    public String uploadFileWithNetFile(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            InputStream inputStream = FileUtils.returnBitMap(url);
            // 判断上传文件是否为空
            if (null == inputStream) {
                return null;
            }
            // 判断存储桶是否存在
            createBucket(MinioConst.MINIO_BUCKET);
            // uuid 生成文件名
            String uuid = String.valueOf(UUID.randomUUID());
            // 新的文件名
            String fileName = "wxAvatar/" + DateUtils.getYmd() + "/" + uuid + ".jpg";
            // 开始上传
            client.putObject(MinioConst.MINIO_BUCKET, fileName, inputStream, "jpg");
            return minioProp.getEndpoint() + "/" + MinioConst.MINIO_BUCKET + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断文件是否为图片
     *
     * @fileSuffix 文件后缀
     */
    public boolean isPicture(String fileSuffix) {
        if (StringUtils.isBlank(fileSuffix)) {
            return false;
        }
        String[] arr = {"bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", "png", "tif", "tiff", "ico"};
        for (String item : arr) {
            if (item.equals(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
}

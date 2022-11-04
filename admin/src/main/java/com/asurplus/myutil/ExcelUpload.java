package com.asurplus.myutil;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ExcelUpload {
    /*
     * 文件上传至服务器   返回文件地址
     * */
    public static String uploadFile(MultipartFile file) {
        int maxSize = 1024 * 1024;    //上传最大为1MB
        /*if (file.getSize()>maxSize) {
            result="最大上传限制1Mb";
            return result;
        }*/
        Map<String, Object> map = new HashMap<String, Object>();
        File targetFile = null;
        String url = "";//返回存储路径
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && !fileName.equals("")) {
            //String path = "/data/file/";
//
            String path = "D:/sprintboot/asurplus-vue-master/admin/src/main/resources/excel/";
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            /*if (!(fileF.equals(".jpg") || fileF.equals(".jpeg") || fileF.equals(".png") || fileF.equals(".image"))) {
                result="只能上传jpg,jpeg,png,image格式";
                return result;
            }*/
            //新的文件名
            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;
            //获取文件夹路径
            File file1 = new File(path);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdirs();
            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                //生成文件地址
                url = path + fileName;
                System.out.println("文件上传成功 url:" + url);
                return url;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}

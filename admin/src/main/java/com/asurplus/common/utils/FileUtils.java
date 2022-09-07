package com.asurplus.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 文件处理工具类
 */
public class FileUtils {

    /**
     * 通过网络地址获取文件InputStream
     *
     * @param path 地址
     * @return
     */
    public static InputStream returnBitMap(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            // 利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            // 得到网络返回的输入流
            is = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

}

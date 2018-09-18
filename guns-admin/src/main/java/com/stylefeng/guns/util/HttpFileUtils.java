package com.stylefeng.guns.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>Title: HttpFileUtils</p>
 * <p>Description: </p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/8/31 0031 23:00
 */
public class HttpFileUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpFileUtils.class);

    /**
     * 通过http地址获取图片二进制流
     * @param url_path 文件所在的http地址
     * @return
     */
    public static InputStream getInputStream(String url_path){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(url_path);//创建的URL
            if (url!=null) {
                httpURLConnection = (HttpURLConnection) url.openConnection();//打开链接
                httpURLConnection.setConnectTimeout(3000);//设置网络链接超时时间，3秒，链接失败后重新链接
                httpURLConnection.setDoInput(true);//打开输入流
                httpURLConnection.setRequestMethod("GET");//表示本次Http请求是GET方式
                int responseCode = httpURLConnection.getResponseCode();//获取返回码
                if (responseCode == 200) {//成功为200
                    //从服务器获得一个输入流
                    inputStream = httpURLConnection.getInputStream();
                }
            }


        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            logger.error("Bad url: {}", e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error("Bad url: {}", e.getMessage());
        } finally {
            return inputStream;
        }
    }

    public static void main(String[] args) {
        String url = "https://cdn.loacg.com/s3-03/01.jpg";
        String path = "F:\\ITMan\\tempFile\\test2.jpg";
        String base64 = Img2Base64Utils.GetImageStr(getInputStream(url));
        Img2Base64Utils.GenerateImage(base64, path);
    }


}

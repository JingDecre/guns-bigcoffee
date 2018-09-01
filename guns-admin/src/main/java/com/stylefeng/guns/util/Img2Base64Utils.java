package com.stylefeng.guns.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * <p>Title: Img2Base64Utils</p>
 * <p>Description: 图片二进制流转化base64</p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/8/31 0031 22:59
 */
public class Img2Base64Utils {

    /**
     * 图片二进制流转化成base64字符串
     * @param in 图片二进制流
     * @return
     */
    public static String GetImageStr(InputStream in)
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        //读取图片字节数组
        try
        {
            int len = -1;
            while ((len = in.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outPut.toByteArray());//返回Base64编码过的字节数组字符串
    }

    /**
     * base64字符串转图片
     * @param imgStr base64字符串
     * @param imgPath 图片存储路径
     * @return
     */
    public static boolean GenerateImage(String imgStr, String imgPath)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = imgPath;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}

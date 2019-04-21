package com.corgi.test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-20 15:27
 **/
public class Base64 {

    /**
     * 将图像文件base64编码为字符串
     * @方法名称: handleImage
     * @方法描述:
     * @param filePath 文件路径
     * @return
     */
    public static String handleImage(String filePath){
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (Exception e) {

        }
        BASE64Encoder encoder = new BASE64Encoder();
        //返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }

    /**
     * base64字符串转化为图片
     * @方法名称: handleString
     * @方法描述:
     * @param imgStr
     * @param savePath
     * @return
     */
    public static boolean handleString(String imgStr,String savePath){
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i){
                //调整异常数据
                if(b[i]<0){
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(savePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args){
        String str = "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";
        handleString(str,"d:\\new.jpg");
    }
}

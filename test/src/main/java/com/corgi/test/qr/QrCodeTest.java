package com.corgi.test.qr;

/**
 * @title: QrCodeTest
 * @description:
 * @author: dengmiao
 * @create: 2019-07-22 09:28
 **/
public class QrCodeTest {

    public static void main(String[] args) {
        try {
            // 存放在二维码中的内容 文本/链接资源路径。。
            String text = "这是二维码的内容";
            // 嵌入二维码的图片路径
            String imgPath = "E:/qrCode/comic.jpg";
            // 生成的二维码的路径及名称
            String destPath = "E:/qrCode/src/"+System.currentTimeMillis()+".jpg";
            //生成二维码
            QrCodeUtil.encode(text, imgPath, destPath, true);
            // 解析二维码
            String str = QrCodeUtil.decode(destPath);
            // 打印出解析出的内容
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

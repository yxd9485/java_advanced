package com.example.multithreading_all.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;

import static cn.hutool.core.img.ImgUtil.toBufferedImage;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/8/16 09:44
 */
public class QRCodeUtil {

    private static final String DEFAULT_IMAGE_FORMAT = "PNG";
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public static BufferedImage createQRCode(String content) throws Exception {
        return createQRCode(content, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static BufferedImage createQRCode(String content, int width, int height) throws Exception {
        BitMatrix matrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        return image;
    }

    public static byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, format, os);
        return os.toByteArray();
    }

    public static String toBase64String(BufferedImage image, String format) throws IOException {
        byte[] bytes = toByteArray(image, format);
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }




    public static void main(String[] args) {
    //    String content = "https://www.baidu.com"; // 要生成二维码的内容

/*        try {
            // 生成二维码图片
            BufferedImage qrCodeImage = QRCodeUtil.createQRCode(content);

            // 将二维码保存为图片文件
            String imagePath = "/Users/yangxiaodong/IdeaProjects/java_advanced/image.png";  // 设置保存路径和文件名
            File outputFile = new File(imagePath);
            ImageIO.write(qrCodeImage, "PNG", outputFile);
            System.out.println("二维码已保存为图片：" + imagePath);

            // 将二维码转换为 Base64 字符串
            String base64Image = QRCodeUtil.toBase64String(qrCodeImage, "PNG");
            System.out.println("Base64 图片数据：" + base64Image);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        // 定义二维码的参数
        int width = 300; // 图片宽度
        int height = 300; // 图片高度
        String format = "jpg"; // 图片格式  如果是png类型，logo图变成黑白的，
        String content = "https://www.baidu.com";// 二维码内容

        // 1.定义HashMap hints
        HashMap hints = new HashMap();
        // 2.hints调用put函数设置字符集、间距以及纠错度为M
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级【L，M，Q，H】
        hints.put(EncodeHintType.MARGIN, 2);
        // 生成二维码
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 3.最后用MultiformatWriter函数类调用echoed函数并返回一个值 然后写入文件
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 这里路径后面的img.png不可省略，前面是自己选取生成的图片地址
            Path file = new File("/Users/yangxiaodong/IdeaProjects/java_advanced/image.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            //*************添加logo*****************
            //读取二维码图片
            BufferedImage bufferedImage = ImageIO.read(new File(file.toString()));
            //获取画笔
            Graphics2D graphics = bufferedImage.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(new File("/Users/yangxiaodong/Desktop/download.jpg"));
            //设置二维码大小，太大了会覆盖二维码，此处为20%
            int logoWidth = logo.getWidth() > bufferedImage.getWidth()*2 /10 ? (bufferedImage.getWidth()*2 /10) : logo.getWidth();
            int logoHeight = logo.getHeight() > bufferedImage.getHeight()*2 /10 ? (bufferedImage.getHeight()*2 /10) : logo.getHeight();
            //设置logo图片放置的位置，中心
            int x = (bufferedImage.getWidth() - logoWidth) / 2;
            int y = (bufferedImage.getHeight() - logoHeight) / 2;
            //开始合并并绘制图片
            graphics.drawImage(logo,x,y,logoWidth,logoHeight,null);
            graphics.drawRoundRect(x,y,logoWidth,logoHeight,15,15);
            //logob边框大小
            graphics.setStroke(new BasicStroke(2));
            //logo边框颜色
            graphics.setColor(Color.WHITE);
            graphics.drawRect(x,y,logoWidth,logoHeight);
            graphics.dispose();
            logo.flush();
            bufferedImage.flush();
            ImageIO.write(bufferedImage, format ,new File("/Users/yangxiaodong/IdeaProjects/java_advanced/image.png"));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

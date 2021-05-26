package cn.lusq.tools.javabase.pdf;

import cn.lusq.tools.framerwork.httpUtil.HttpClient.HttpClientUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * lusq
 * 2021/5/25 9:24
 */
public class PdfToImage {



    public static List<String> pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) {
        List<String>images = new ArrayList<>();
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            // 获取图片文件名
            String imagePDFName = file.getName().substring(0, dot);
            String imgFolderPath = dstImgFolder;

            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            int pageCount = pdDocument.getNumberOfPages();
            /* dpi越大转换后越清晰，相对转换速度越慢 */
            StringBuffer imgFilePath = null;
            for (int i = 0; i < pageCount; i++) {
                String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                imgFilePath = new StringBuffer();
                imgFilePath.append(imgFilePathPrefix);
                imgFilePath.append(".png");
                File dstFile = new File(imgFilePath.toString());
                BufferedImage image = renderer.renderImageWithDPI(i, dpi);

                //io流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //写入流中
                ImageIO.write(image, "png", baos);
                //转换成字节
                byte[] bytes = baos.toByteArray();
                BASE64Encoder encoder = new BASE64Encoder();
                //转换成base64串
                String png_base64 = encoder.encodeBuffer(bytes).trim();
                //删除 \r\n
                png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
                images.add(png_base64);
            }
            System.out.println("PDF文档转PNG图片成功！");
            return images;
//            return imgFilePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws IOException {
/*        String wpath = "D:\\";
        String s = "D:\\dikoujinhetong.pdf";
//        String s = wpath + BRCA + "-" + CTNND1_15951 + "-KMplot.pdf";
        //转换返回图片地址
        List<String> s1 = pdf2Image(s, wpath, 100);

        for (int i=0;i<s1.size();i++){
            System.out.println(s1.get(i));
            System.out.println("====================================================================");
        }*/

        String s = "D:\\dikoujinhetong.pdf";
//        pdfToImage(s);

        String s1 = pdfToImage2(s, 100);
        System.out.println(s1);


//        File file = new File(s1);
//把图片转换为BASE64数据
      /*  FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        String ss = new BASE64Encoder().encode(buffer);
        System.out.println(ss);*/


    }


    public static void pdfToImage(String pdfPath)throws IOException {
        try {
            /*图像合并使用参数*/
            // 定义宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            // 定义高度，后面用于叠加
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
            // 每50页转成1张图片
            int pageLength = 50;
            // 总计循环的次数
            int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
            for (int m = 0; m < totalCount; m++) {
                for (int i = 0; i < pageLength; i++) {
                    int pageIndex = i + (m * pageLength);
                    if (pageIndex == pdDocument.getNumberOfPages()) {
                        System.out.println("m = " + m);
                        break;
                    }
                    // 96为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, 96, ImageType.RGB);
                    int imageHeight = image.getHeight();
                    int imageWidth = image.getWidth();
                    if (i == 0) {
                        //计算高度和偏移量
                        //使用第一张图片宽度;
                        width = imageWidth;
                        // 保存每页图片的像素值
                        // 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
                        if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
                            imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
                        } else {
                            imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
                        }
                    } else {
                        // 将高度不断累加
                        shiftHeight += imageHeight;
                    }
                    singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                    imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
                }
                System.out.println("m = " + m);
                File outFile = new File(pdfPath.replace(".pdf", "_" + m + ".jpg"));
                System.out.println(outFile.getName());
                // 写图片
                ImageIO.write(imageResult, "jpg", outFile);
                // 这个很重要，下面会有说明
                shiftHeight = 0;
            }
            pdDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }







    public static String pdfToImage2(String pdfPath, int dpi)throws IOException {
        try {
            /*图像合并使用参数*/
            // 定义宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            // 定义高度，后面用于叠加
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            // 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            /*根据总页数, 按照50页生成一张长图片的逻辑, 进行拆分*/
            // 每50页转成1张图片
            int pageLength = 50;
            // 总计循环的次数
            int totalCount = pdDocument.getNumberOfPages() / pageLength + 1;
            String png_base64 ="";
            for (int m = 0; m < totalCount; m++) {
                for (int i = 0; i < pageLength; i++) {
                    int pageIndex = i + (m * pageLength);
                    if (pageIndex == pdDocument.getNumberOfPages()) {
                        System.out.println("m = " + m);
                        break;
                    }
                    // 96为图片的dpi，dpi越大，则图片越清晰，图片越大，转换耗费的时间也越多
                    BufferedImage image = renderer.renderImageWithDPI(pageIndex, dpi, ImageType.RGB);
                    int imageHeight = image.getHeight();
                    int imageWidth = image.getWidth();
                    if (i == 0) {
                        //计算高度和偏移量
                        //使用第一张图片宽度;
                        width = imageWidth;
                        // 保存每页图片的像素值
                        // 加个判断：如果m次循环后所剩的图片总数小于pageLength，则图片高度按剩余的张数绘制，否则会出现长图片下面全是黑色的情况
                        if ((pdDocument.getNumberOfPages() - m * pageLength) < pageLength) {
                            imageResult = new BufferedImage(width, imageHeight * (pdDocument.getNumberOfPages() - m * pageLength), BufferedImage.TYPE_INT_RGB);
                        } else {
                            imageResult = new BufferedImage(width, imageHeight * pageLength, BufferedImage.TYPE_INT_RGB);
                        }
                    } else {
                        // 将高度不断累加
                        shiftHeight += imageHeight;
                    }
                    singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                    imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
                }
                System.out.println("m = " + m);
                //io流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //写入流中
                ImageIO.write(imageResult, "png", baos);
                //转换成字节
                byte[] bytes = baos.toByteArray();
                BASE64Encoder encoder = new BASE64Encoder();
                //转换成base64串
                png_base64 += encoder.encodeBuffer(bytes).trim().replaceAll("\n", "").replaceAll("\r", "");
                //删除 \r\n
                // 这个很重要，下面会有说明
                shiftHeight = 0;
            }
            pdDocument.close();
            return  png_base64;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
































}

package cn.lusq.tools.javabase.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * lusq
 * 2021/5/25 17:22
 */
public class Pdf2Img4 {



    public static File pdf2image(File pdfFile) throws Exception {
        // 拼成图片后的宽度和高度
        int w = 0;
        int h = 0;
        java.util.List<BufferedImage> images = (java.util.List<BufferedImage>) new List();
        // 生成图片后的路径
        String path = pdfFile.getParent() + File.separator;
        String fileName = pdfFile.getName().replace(".pdf", "");

        File destinationFile = new File(path);
        if (!destinationFile.exists()) {
            destinationFile.mkdir();
        }
        PDDocument document = PDDocument.load(pdfFile);
        PDPageTree list = document.getDocumentCatalog().getPages();
        int pageCounter = 0;
        for (PDPage page : list) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(pageCounter, 100, ImageType.RGB);
            String target = path + fileName + "-" + (pageCounter++) + ".png";
            ImageIOUtil.writeImage(image, target, 100);

            w = image.getWidth();
            h += image.getHeight();
            images.add(image);
            new File(target).delete();
        }
        document.close();

        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();

        int y = 0;
        for (BufferedImage image : images) {
            g.drawImage(image, 0, y, null);
            y += image.getHeight();
        }

        // Save as new image
        File image = new File(path, fileName + ".png");
        ImageIO.write(combined, "PNG", image);
        return image;
    }


    public static void main(String[] args) throws Exception {
        String s = "D:\\dikoujinhetong.pdf";
        String out = "D:\\demoPrc";

        File file = new File(s);
        File file1 = pdf2image(file);







    }




}

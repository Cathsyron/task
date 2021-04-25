/**
 * 在pdf上操作
 * 原理是操作pdf上的电子标签
 * 目前没用到，不保证能跑通
 */
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.*;
//import com.itextpdf.text.pdf.parser.PdfImageObject;
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class OperatePdf {
////
//public static class ReplaceStream {
//    public static final String DEST = "C:\\Users\\luocj38013\\Desktop\\original.pdf";
//    public static final String SRC = "C:\\Users\\luocj38013\\Desktop\\changed.pdf";
//
//    public static void main(String[] args) throws Exception {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//        new ReplaceStream().manipulatePdf(DEST);
//    }
//
//    protected void manipulatePdf(String dest) throws Exception {
//        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
//        PdfPage page = pdfDoc.g();
//        PdfDictionary dict = page.getPdfObject();
//        PdfObject object = dict.get(PdfName.Contents);
//        if (object instanceof PdfStream) {
//            PdfStream stream = (PdfStream) object;
//            byte[] data = stream.getBytes();
//            stream.setData(new String(data).replace("Hello World", "HELLO WORLD").getBytes("UTF-8"));
//        }
//        pdfDoc.close();
//    }
//}
////    public static class ReplaceImage {
////
////        public static final String SRC = "C:\\Users\\luocj38013\\Desktop\\original.pdf";
////        public static final String DEST = "C:\\Users\\luocj38013\\Desktop\\changed.pdf";
////
////        public static void main(String[] args) throws DocumentException, IOException {
////            File file = new File(DEST);
////            //file.getParentFile().mkdirs();
////            new ReplaceImage().manipulatePdf(SRC, DEST);
////        }
////
////        public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
////            PdfReader reader = new PdfReader(src);
////            PdfDictionary page = reader.getPageN(1);
////            PdfDictionary resources = page.getAsDict(PdfName.RESOURCES);
////            PdfDictionary xObjects = resources.getAsDict(PdfName.XOBJECT);
////            //itext5添加的图片元素为/FXX开头不一定为第一个，因此取最后一个，itext7就没有这个困扰
////            //PdfDictionary xObjects = null;
////            PdfName imgRef = (PdfName) xObjects.getKeys().toArray()[xObjects.getKeys().size()-1];
////            PRStream stream = (PRStream) xObjects.getAsStream(imgRef);
////            PdfImage image = new PdfImage(makeBlackAndWhitePng(new PdfImageObject(stream)), "", null);
////            replaceStream(stream, image);
////            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
////            stamper.close();
////            reader.close();
////        }
////
////        public static Image makeBlackAndWhitePng(PdfImageObject image) throws IOException, DocumentException {
////            BufferedImage bi = image.getBufferedImage();
////            BufferedImage newBi = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
////            newBi.getGraphics().drawImage(bi, 0, 0, null);
////            ByteArrayOutputStream baos = new ByteArrayOutputStream();
////            ImageIO.write(newBi, "png", baos);
////            return Image.getInstance(baos.toByteArray());
////        }
////        public static void replaceStream(PRStream orig, PdfStream stream) throws IOException {
////            orig.clear();
////            ByteArrayOutputStream baos = new ByteArrayOutputStream();
////            stream.writeContent(baos);
////            orig.setData(baos.toByteArray(), false);
////            for (PdfName name : stream.getKeys()) {
////            orig.put(name, stream.get(name));
////            }
////        }
////    }
//}

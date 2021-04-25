//import com.itextpdf.awt.geom.Rectangle2D;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//import com.itextpdf.text.pdf.parser.ImageRenderInfo;
//import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
//import com.itextpdf.text.pdf.parser.RenderListener;
//import com.itextpdf.text.pdf.parser.TextRenderInfo;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * 往pdf上盖公章
// */
//public class StampOfficialSteal {
//
//    /**
//     * 根据pdf中的关键字，获取文字的绝对位置，并进行签章
//     * @param inputPath  未处理pdf
//     * @param targetPath 已签章pdf地址
//     * @param imagePath  签章图片地址
//     * @param inputPath  pdf中的关键字
//     * @param pageNum    pdf页数,可传null，默认设置最大页数
//     * @return float的x与y值
//     * @throws IOException
//     */
//    public static void addSignImg(String inputPath,
//                                  String targetPath,
//                                  final String imagePath,
//                                  final String keyWord,
//                                  Integer pageNum
//    ) throws IOException, DocumentException {
//        PdfReader pdfReader = new PdfReader(inputPath);
//        // 读图片
//        final Image image = Image.getInstance(imagePath);
//        // 根据域的大小缩放图片
//        image.scaleToFit(120, 120);
//
//        if (null == pageNum) {
//            pageNum = pdfReader.getNumberOfPages();
//        }
//        new PdfReaderContentParser(pdfReader).processContent(pageNum, new RenderListener() {
//            public void beginTextBlock() {
//
//            }
//
//            public void renderText(TextRenderInfo textRenderInfo) {
//                String text = textRenderInfo.getText();
//                if (text != null && text.contains(keyWord)) {
//                    // 文字在page中的横坐标、纵坐标
//                    Rectangle2D.Float textFloat = textRenderInfo.getBaseline().getBoundingRectange();
//                    float x = textFloat.x;
//                    float y = textFloat.y;
//                    // 设置图片位置
//                    image.setAbsolutePosition(x + 50f, y - 30f);
//                }
//            }
//
//            public void endTextBlock() {
//
//            }
//
//            public void renderImage(ImageRenderInfo renderInfo) {
//
//            }
//        }
//        );
//        // 获取操作的页面
//        PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(targetPath));
//        PdfContentByte under = stamper.getOverContent(pageNum);
//        image.setAbsolutePosition(50, 300);
//        under.addImage(image);
//        stamper.close();
//        pdfReader.close();
//    }
//}
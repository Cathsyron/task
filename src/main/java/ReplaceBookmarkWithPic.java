import org.apache.commons.io.IOUtils;
import org.docx4j.Docx4J;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.dml.Graphic;
import org.docx4j.dml.GraphicData;
import org.docx4j.dml.picture.Pic;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.RangeFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luocj38013
 * @date 2021/4/23 9:01
 */
public class ReplaceBookmarkWithPic {
    private static ObjectFactory factory;
    private static WordprocessingMLPackage wdp;

    public static void replace(Map<String,String> map,ArrayList bmlist) throws Exception {

        wdp =WordprocessingMLPackage.load(new File(Path.OriginalWordPath));
        MainDocumentPart mainDocumentPart=wdp.getMainDocumentPart();
        factory = Context.getWmlObjectFactory();

        Document document=mainDocumentPart.getJaxbElement();
        Body body=document.getBody();
        List<Object> paragraphs = body.getContent();
        RangeFinder rt = new RangeFinder("CTBookmark","CTMarkupRange");
        new TraversalUtil(paragraphs,rt);

        for(CTBookmark bm:rt.getStarts()){
            for(Object bmName: bmlist){
                if(bm.getName().equals(bmName)){
                    replaceText(bm,map.get(bm.getName()));
                }
            }
            if(bm.getName().equals("Sign1_af_image")){
                replaceText(bm,"");
                addImage(wdp,bm,Path.PictureOnePath);
            }
            if(bm.getName().equals("Sign2_af_image")){
                replaceText(bm,"");
                addImage(wdp,bm,Path.PictureTwoPath);
            }
            if(bm.getName().equals("Sign3_af_image")){
                replaceText(bm,"");
                addImage(wdp,bm,Path.PictureThreePath);
            }
            if(bm.getName().equals("Sign4_af_image")){
                replaceText(bm,"");
                addImage(wdp,bm,Path.PictureFourPath);
            }
        }
        Docx4J.save(wdp, new File(Path.AddPicturePath));
    }

    private static void addImage(WordprocessingMLPackage wdp, CTBookmark bm, String picturePath) throws Exception {
        P p = (P) (bm.getParent());
//        Long newCx = 0L;
//        Long newCy = 0L;
//        String newRelId = null;
        R run = factory.createR();
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(picturePath));
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wdp, bytes);
        //maxWidth调整图片大小
        Inline inline = imagePart.createImageInline(null, null, 1, 1, false, 1000);
        Drawing drawing = factory.createDrawing();
        drawing.getAnchorOrInline().add(inline);
        run.getContent().add(drawing);
        p.getContent().add(run);
//        //获取欲插入图片的CX 、CY
//        newCx = inline.getExtent().getCx();
//        newCy = inline.getExtent().getCy();
//        //获取relId
//        Graphic newg = inline.getGraphic();
//        GraphicData graphicdata = newg.getGraphicData();
//        Object o = graphicdata.getAny().get(0);
//        Pic pic = (Pic)XmlUtils.unwrap(o);
//        newRelId = pic.getBlipFill().getBlip().getEmbed();
//        //获取word图片的CX 、CY 、relId
//        Object parent = (P) bm.getParent();
//        List<Object> rList = getAllElementFromObject(parent, R.class);
//        for (Object robj: rList){
//            if (robj instanceof R){
//                R r = (R) robj;
//                List<Object> drawList = getAllElementFromObject(r, Drawing.class);
//                for (Object dobj: drawList){
//                    if (dobj instanceof Drawing){
//                        Drawing d = (Drawing) dobj;
//                        Inline ninline = (Inline)d.getAnchorOrInline().get(0);
//                        // 获取CX 、CY
//                        Long cx = ninline.getExtent().getCx();
//                        Long cy = ninline.getExtent().getCy();
//                        //System.out.printf("word图片的位置xy",cx,cy);
//                        // 获取relId
//                        Graphic g = ninline.getGraphic();
//                        GraphicData graphicdataa = g.getGraphicData();
//                        Object oj = graphicdataa.getAny().get(0);
//                        Pic picc = (Pic)XmlUtils.unwrap(oj);
//                        String relId = picc.getBlipFill().getBlip().getEmbed();
//
//                        // 替换relId
//                        picc.getBlipFill().getBlip().setEmbed(newRelId);
//                        // 处理CX、CY
//                        Map<String,Long> map = dealCxy(cx, cy, newCx, newCy);
//
//                        // 更改cx、cy
//                        inline.getExtent().setCx(map.get("setCx"));
//                        inline.getExtent().setCy(map.get("setCy"));
//
//                    }
//                }
//            }
//        }
    }
    private static void replaceText(CTBookmark bm, Object object) {
        String value = object.toString();
        List<Object> theList = null;
        ParaRPr rpr = null;
        if (bm.getParent() instanceof P) {
            PPr pprTemp = ((P) (bm.getParent())).getPPr();
            if (pprTemp == null) {
                rpr = null;
            } else {
                rpr = ((P) (bm.getParent())).getPPr().getRPr();
            }
            theList = ((ContentAccessor) (bm.getParent())).getContent();
        } else {
            return;
        }
        int rangeStart = -1;
        int rangeEnd = -1;
        int i = 0;
        for (Object ox : theList) {
            Object listEntry = XmlUtils.unwrap(ox);
            if (listEntry.equals(bm)) {
                if (((CTBookmark) listEntry).getName() != null) {
                    rangeStart = i + 1;
                }
            } else if (listEntry instanceof CTMarkupRange) {
                if (((CTMarkupRange) listEntry).getId().equals(bm.getId())) {
                    rangeEnd = i - 1;
                    break;
                }
            }
            i++;
        }
        int x = i - 1;
        for (int j = x; j >= rangeStart; j--) {
            theList.remove(j);
        }
        org.docx4j.wml.R run = factory.createR();
        org.docx4j.wml.Text t = factory.createText();
        t.setValue(value);
        run.getContent().add(t);
        theList.add(rangeStart, run);
    }
}


    /**
     * 处理图片自适应大小
     * @param cx
     * @param cy
     * @param newCx
     * @param newCy
     */
//    public static Map<String, Long> dealCxy(Long cx, Long cy, Long newCx, Long newCy){
//        Map<String, Long> map = new HashMap<>();
//        Long setCx;
//        Long setCy;
//
//        if (newCx > cx){
//            if (newCy <= cy){
//                setCx = cx;
//                setCy = newCy/(newCx/cx);
//            } else {
//                if ((newCx/cx) > (newCy/cy)){
//                    setCx = cx;
//                    setCy = newCy/(newCx/cx);
//                } else {
//                    setCy = cy;
//                    setCx = newCx/(newCy/cy);
//                }
//            }
//        } else {   // newCx < cx
//            if (newCy > cy) {
//                setCx = cx;
//                setCy = newCy * (cx/newCx);
//            } else {
//                if ((cx/newCx) > (cy/newCy)) {
//                    setCx = cx;
//                    setCy = newCy *(cx/newCx);
//                } else {
//                    setCy = cy;
//                    setCx = newCy * (cy/newCy);
//                }
//            }
//        }
//        map.put("setCx",setCx);
//        map.put("setCy",setCy);
//        return map;
//    }
//    public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
//        List<Object> result = new ArrayList<>();
//        if (obj instanceof JAXBElement)
//            obj = ((JAXBElement<?>) obj).getValue();
//        if (obj.getClass().equals(toSearch))
//            result.add(obj);
//        else if (obj instanceof ContentAccessor) {
//            List<?> children = ((ContentAccessor) obj).getContent();
//            for (Object child : children) {
//                result.addAll(getAllElementFromObject(child, toSearch));
//            }
//        }
//        return result;
//    }
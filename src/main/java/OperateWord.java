/**
 * 在word上通过${var}来实现文本的替换
 * 目前没用到
 */
//import org.docx4j.Docx4J;
//import org.docx4j.openpackaging.exceptions.Docx4JException;
//import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
//import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
//import javax.xml.bind.JAXBException;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class OperateWord {
//    public static WordprocessingMLPackage replace(WordprocessingMLPackage wpg, Map<String,String> map, ArrayList<String> bmlist) throws Docx4JException, JAXBException {
//
//        String inputPath = Path.OriginalWordPath;//word输入地址
//        String outPath=Path.ReplaceTextPath;//word输出位置
//        //通过特定处理格式读入模板文件
//        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(inputPath));
//        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();//读入的word有哪些部分
//        /**
//         * 与模板word中的${}做变量替换
//         * ${}变量需要从左往右依次输入
//         */
//        HashMap<String, String> mappings = new HashMap<String, String>();//映射
//        mappings.put("Year", "2021");
//        mappings.put("Month","04");
//        mappings.put("Day", "19");
//        mappings.put("AssetName", "基金");
//        mappings.put("AssetCode", "330681");
//        mappings.put("Number", "034");
//        documentPart.variableReplace(mappings);//替换
//        Docx4J.save(wordMLPackage, new File(outPath));//保存
//
//    }
//}

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.log4j.BasicConfigurator;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperateMain {
    public static void main(String[] args) throws Exception {

        BasicConfigurator.configure(); //使用默认的配置信息，不需要写log4j.properties

        Map<String,String> map = new HashMap<>();
        map.put("sAcceptAccount","13627576481");//收款账户
        map.put("sAcceptAccountName","张三");//收款户名
        map.put("sAcceptBankName","浙江招商银行");//收款方开户行
        map.put("sAcceptPayNo","0000000000");//收款方大额号
        map.put("sBankName","aaaaaaaaaa");//托管行名称
        map.put("sBigSettleBalance","壹贰叁肆伍");//付款金额大写
        map.put("sCapitalAccountNo","account");//资金账号
        map.put("sCashPurpose","资金转出");//划款事由
        map.put("sCompanyName","恒生电子");//公司名称
        map.put("sCurrencyName","人民币");//币种名称
        map.put("sCurrencySign","￥");//币种符号
        map.put("sCustodianName","李四");//托管人全称
        map.put("sDailyId","003");//每日指令编号
        map.put("sDate","20210423");//年月日
        map.put("sDay","0425");//日期
        map.put("sFareBeginDate","0425");//费用开始日期
        map.put("sFareEndDate","0426");//费用结束日期
        map.put("sFundCode","cx123");//基金代码
        map.put("sFundFullName","a啊啊啊");//基金名称（全称）
        map.put("sFundName","a");//基金名称（简称）
        map.put("sInvestType","金融");//投资类型
        map.put("sMemo","没有备注");//备注
        map.put("sMonth","04");//月份
        map.put("sPayAccount","qqqqqqq");//付款账户
        map.put("sPayAccountName","1234241323");//付款户名
        map.put("sPayBankName","央行");//付款方开户行
        map.put("sPayDate","0411");//付款日期
        map.put("sPayMemo","jjjj");//划款附言
        map.put("sPayNo","12324231");//付款方大额号
        map.put("sPayTime","0412");//付款时间
        map.put("sSerialNo","4637848");//划款单编号
        map.put("sSettleBalance","10");//付款金额
        map.put("sTransferId","0004");//指令编号
        map.put("sTransferRemark","hhhhh");//划款备注
        map.put("sYear","2021");//年份
        map.put("sYear1","2021");//年份

        ArrayList<String> bmList = new ArrayList<>();
        bmList.add("sAcceptAccount");
        bmList.add("sAcceptAccountName");
        bmList.add("sAcceptBankName");
        bmList.add("sAcceptPayNo");
        bmList.add("sBankName");
        bmList.add("sBigSettleBalance");
        bmList.add("sCapitalAccountNo");
        bmList.add("sCashPurpose");
        bmList.add("sCompanyName");
        bmList.add("sCurrencyName");
        bmList.add("sCurrencySign");
        bmList.add("sCustodianName");
        bmList.add("sDailyId");
        bmList.add("sDate");
        bmList.add("sDay");
        bmList.add("sFareBeginDate");
        bmList.add("sFareEndDate");
        bmList.add("sFundCode");
        bmList.add("sFundFullName");
        bmList.add("sFundName");
        bmList.add("sInvestType");
        bmList.add("sMemo");
        bmList.add("sMonth");
        bmList.add("sPayAccount");
        bmList.add("sPayAccountName");
        bmList.add("sPayBankName");
        bmList.add("sPayDate");
        bmList.add("sPayMemo");
        bmList.add("sPayNo");
        bmList.add("sPayTime");
        bmList.add("sSerialNo");
        bmList.add("sSettleBalance");
        bmList.add("sTransferId");
        bmList.add("sTransferRemark");
        bmList.add("sYear");
        bmList.add("sYear1");

        /**
         * Create the official steal
         * 生成公章图片
         */
//        Date dNow = new Date( );
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy年MM月dd日");
//        String year = ft.format(dNow);
//        BufferedImage image = CreateOfficialSteal.startGraphics2D("恒生电子股份有限公司","合同专用章",year);
//        String filePath = Path.StealPath;
//        ImageIO.write(image, "png", new File(filePath));

        /**
         * Operate the template word
         * word内替换书签内容
         */
//        try {
//            OperateWord.replace();
//        } catch (Docx4JException e) {
//            e.printStackTrace();
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
        ReplaceBookmarkWithPic.replace(map,bmList);

        /**
         * Transform word to pdf
         * word转pdf
         */
        FileInputStream fileInputStream = new FileInputStream(Path.AddPicturePath);
        XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
        PdfOptions pdfOptions = PdfOptions.create();
        FileOutputStream fileOutputStream = new FileOutputStream(Path.TransformPdfPath);
        PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,pdfOptions);
        fileInputStream.close();
        fileOutputStream.close();

        /**
         * Stamp the steal on the pdf
         * 在pdf上盖章
         */
//        // 模板文件路径
//        String templatePath = Path.OldPdfPath;
//        // 生成的文件路径
//        String targetPath = Path.NewPdfPath;
//        // 图片路径, 需要png透明图片,否则会覆盖文字
//        String imagePath = Path.StealPath;
//        try {
//            StampOfficialSteal.addSignImg(templatePath, targetPath, imagePath, "复核人", null);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }

    }
}

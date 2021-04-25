/**
 * fixed route
 * 文件路径
 */
public class Path {
    /**
     * 公章图片所在位置
     */
    public static String PictureOnePath = ".\\src\\main\\resources\\cm.png";//图片地址一
    public static String PictureTwoPath = ".\\src\\main\\resources\\dj.png";//图片地址二
    public static String PictureThreePath = ".\\src\\main\\resources\\lxc.png";//图片地址三
    public static String PictureFourPath = ".\\src\\main\\resources\\zhsh.png";//图片地址四

    /**
     * 要操作的word及pdf地址
     */
    public static String OriginalWordPath=".\\src\\main\\resources\\original.docx";//模板word
    public static String AddPicturePath = ".\\src\\main\\resources\\changed.docx";//模板文件用图片替换书签后的保存地址
    public static String TransformPdfPath=".\\src\\main\\resources\\end.pdf";//仅进行word转pdf

//    public static String NewPdfPath="C:\\Users\\luocj38013\\Desktop\\new.pdf";//word转pdf+未盖章的pdf，此步不操作
//    public static String StealPath="C:\\Users\\luocj38013\\Desktop\\steal.png";//公章图片位置
}

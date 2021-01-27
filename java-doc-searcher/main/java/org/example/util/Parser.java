package org.example.util;

/**
 * ClassName org.example.util
 * Description TODO  预处理模块
 * Author 30712
 * Date 2021-01-25
 * Time 9:04
 */

import org.example.model.DocInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 步骤1：从本地的api目录，遍历html文件
 * 每一个html文件构建正文索引：本地某个文件
 * 正文索引信息 List<DocInfo>
 *     DocInfo（id,title,content,url）
 */
public class Parser {
    //api目录
    public static  final String API_PATH = "D:\\Bit\\docs\\api";
    //构建的本地文件正排索引
    public static final String  RAW_DATA = "D:/raw_data.txt";
    //官方api文档根路径
    public static final  String API_BASE_PATH = "https://docs.oracle.com/javase/8/docs/api";

    public static void main(String[] args) throws IOException{
        //找到api本地路径下所有的html文件
        List<File> htmls = listHtml(new File(API_PATH));//递归调用

        FileWriter fw = new FileWriter(RAW_DATA);//
        //BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(fw,true);

        for (File html: htmls){
            //测试一下：获取目录下的所有html文件没有问题
           // System.out.println(html.getAbsolutePath());

            //一个html解析DocInfo有的属性
            DocInfo doc = parseHtml(html);
            //验证：解析的doc是否正确
            System.out.println(doc);
            // 保存本地正排索引文件:输出流输出到一个目标文件
            //输出的格式：一行数据为一个doc，title+\3+url+\3+content
            System.out.println("Parse:"+html.getAbsolutePath().substring(API_PATH.length()));
            pw.println(doc.getTitle()+"\3"+doc.getUrl()+"\3"+doc.getContent());
        }

    }

    private static DocInfo parseHtml(File html) {
        DocInfo doc = new DocInfo();
        doc.setTitle(html.getName().substring(0,html.getName().length()-".html".length()));
        //获取相对路径：/java/lang/String.html
        String url = html.getAbsolutePath().substring(API_PATH.length());
        doc.setUrl(API_BASE_PATH+url);
        doc.setContent(parseContent(html));//设置正文
        return doc;//目前树丛本地api目录的html文件解析为文档对象，不需要设置id
    }

    /**
     * 解析html文件内容：
     * <标签> 内容</标签>
     * 只取内容，有多个标签就拼接
     * @param html
     * @return
     */
    private static String parseContent(File html)  {
        StringBuilder sb = new StringBuilder();
        //io流来处理
        try {
            FileReader fr = new FileReader(html);
            int i ;
            boolean isContent = false;
            //一个字符一个字符来读取
            while ((i = fr.read())  != -1){
                char c = (char) i;
                //根据读取的字符c是否是'<','>',isContent决定是否拼接字符串
               if ( isContent){
                   if (c == '<'){//表示正文结束（当前标签的内容读取结束）
                       isContent = false;
                       continue;
                   }else if(c == '\n' || c == '\r'){
                       sb.append(" ");
                   }else {
                       //进行字符拼接
                       sb.append(c);//拼接标签内容
                   }
               }else if (c == '>'){//当前不是正文，并且读取到'>'，之后就是正文
                   isContent = true;
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //递归遍历html文件
    private static List<File>  listHtml(File dir){
        List<File> list = new ArrayList<>();
        File[] children = dir.listFiles();//子文件夹和子文件

        for (File child : children){
            if (child.isDirectory()){//子文件夹：递归调用获取子文件夹内的html文件
                list.addAll(listHtml(child));
            }else  if (child.getName().endsWith(".html")){//是html文件
                list.add(child);
            }
        }
        return list;
    }
}

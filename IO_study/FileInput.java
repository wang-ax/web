import java.io.*;

/**
 * ClassName PACKAGE_NAME
 * Description TODO
 * 文件输入字节流
 * 文件输入字符流
 * 缓冲流：缓冲字节输入、缓冲字符输入
 * Author 30712
 * Date 2021-01-18
 * Time 17:17
 */
public class FileInput {
    public static void main(String[] args) throws IOException {
        //File file  = new File("C:\\Users\\30712\\Pictures\\Saved Pictures\\QQ图片20201127155250.jpg");
        File file  = new File("C:\\Users\\30712\\Pictures\\Saved Pictures\\新建文本文档.txt");

        //1.文件输入字节流
        FileInputStream  fis = new FileInputStream(file);
        //输入流比较固定的写法，读取到一个字节/字符数组，定义read的返回值变量，while
//        byte[] bytes = new byte[1024];
//        int len =0;
//        //读取到的长度，数组可能读满，也可能没读满，当此读取内容，一般使用数组[0,len]
//        while ((len= fis.read(bytes)) != -1){
//            String string = new String(bytes,0,len);//字节数组转字符串，模拟
//            System.out.println(string);
//        }
        //一般来说，输入输出流使用完之后一定要关闭，关闭的顺序是反向关闭（和创建的顺序相反）

        //2.文件输入字符流
        FileReader fr = new FileReader(file);
        char[] chars = new char[1024];
        int len =0;
        while ((len = fr.read(chars)) != -1){
            String string = new String(chars,0,len);
            System.out.println(string);
        }

        //3.缓冲流：缓冲字节输入、缓冲字符输入
        FileInputStream fileInputStream = new FileInputStream(file);//文件字节输入流
        //
        //字节流转字符流，一定要经过字节字符准换流来转换，并且可以指定编码
        //和文件的编码格式一致，否则会是乱码，默认是原先配置好的
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String string;
        while ((string = bufferedReader.readLine()) != null){
            System.out.println(string);
        }
        //释放资源，反向释放
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();



        //3.缓冲流：缓冲字节输入
        //缓冲的字节输入流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

    }


}

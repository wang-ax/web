import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName PACKAGE_NAME
 * Description TODO
 * Author 30712
 * Date 2021-01-18
 * Time 20:51
 */
public class FileCopy {
    //文件复制
    public static void main(String[] args) throws IOException {
        File input = new File("D:\\Bit\\资料\\软件安装包\\ideaIU-2020.1.3.exe");
        File output = new File("D:\\idea.exe");
        if (!output.exists()){
            output.createNewFile();
        }
        //定义输入输出流
        FileInputStream fis = new FileInputStream(input);
        FileOutputStream fos = new FileOutputStream(output);
        long start = System.currentTimeMillis();
        byte[] bytes = new byte[1024*8];
        int len =0;
        while ((len = fis.read(bytes)) != -1){
            //每次从输入流读取到byte[]的内容，直接输出到某个文件就是复制
            fos.write(bytes,0,len);
        }
        long end = System.currentTimeMillis();//执行时间
        System.out.println(end-start);
        fis.close();;
        fos.close();

        //可以使用缓冲字节输入流、缓冲字节输出流来复制
        //new BufferInputStream (new FileInputStream())
        //new BufferOutputStream (new FileInputStream())


    }
}

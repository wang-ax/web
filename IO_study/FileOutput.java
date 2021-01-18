import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName PACKAGE_NAME
 * Description TODO
 * Author 30712
 * Date 2021-01-18
 * Time 20:50
 */
public class FileOutput {
    public static void main(String[] args) throws IOException {
        //路径上没有该文件，new File操作不会报错，但是操作输入输出流会抛出异常FileNotFoundException
        File file = new File("D:/1.txt");
        //输入a-z换行输出到某个文件，需要考虑文件是否存在的问题
        if (!file.exists()){
            file.createNewFile();
        }
        //类似输入的几种写法都可以
        //new FileWriter()/new FileOutputStream()/new BufferedWriter()/new PrintWriter
        //
        //缓冲字符输出，API好调用
        /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write("\n");*/

        //打印输出流
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
        //快速打印a-z
        for (int i ='a';i<= 'z';i++){
            printWriter.println((char)i);
        }
        printWriter.flush();


    }
}

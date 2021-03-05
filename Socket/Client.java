import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClassName PACKAGE_NAME
 * Description TODO
 * Author 30712
 * Date 2021-03-04
 * Time 20:19
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        // socket 已经建立好连接了

        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8");

        OutputStream outputStream = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
        PrintWriter printWriter = new PrintWriter(writer);

        printWriter.printf("你好\r\n"); // 向服务器发送消息
        printWriter.flush();    // 只要进行了刷新(flush)操作，才能把数据真正写入

        String message = scanner.nextLine(); //从服务器读取消息
        System.out.println(message);//message是从服务端读取的数据

        socket.close();
    }
}

package h1;

import java.io.*;
import java.net.Socket;

/**
 * ClassName h1
 * Description TODO
 * Author 30712
 * Date 2021-03-04
 * Time 20:58
 */
public class RequestResponseTask  implements  Runnable{
    private  final  Socket socket;

    public RequestResponseTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("一条TCP请求连接");

            //直接写回响应
            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream,"UTF-8");
            PrintWriter printWriter = new PrintWriter(writer);

            Thread.sleep(10000);


            //写响应行
            printWriter.printf("HTTP/1.0 200 OK\r\n");
            //写响应头
            printWriter.printf("Content-Type:text/html;charset=utf-8\r\n");
            //空行,代表响应头结束
            printWriter.printf("\r\n");
            //响应体，html内容
            printWriter.printf("<h1>正常工作</h1>");
            //刷新，把数据写入TCP发送缓冲区
            printWriter.flush();
        } catch (IOException | InterruptedException e) {
            //单次的请求响应周期错误，不应该影响其他的请求响应周期，不能让程序停止，打印出异常即可
            e.printStackTrace(System.out);
        }finally {
            try{
                socket.close();
                System.out.println("一条TCP连接已经释放");
            }catch (IOException e){
                e.printStackTrace(System.out);
            }
        }
    }
}

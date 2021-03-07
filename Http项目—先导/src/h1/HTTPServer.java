package h1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName h1
 * Description TODO
 * Author 30712
 * Date 2021-03-04
 * Time 20:33
 */
public class HTTPServer {
    /*public static void main(String[] args) throws IOException {
        //多线程版本：
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket socket =serverSocket.accept();

            Runnable task = new RequestResponseTask(socket);
            new Thread(task).start();
        }
    }*/

    //线程池
    public static void main(String[] args) throws IOException {
        //固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket socket =serverSocket.accept();
            Runnable task = new RequestResponseTask(socket);
            threadPool.execute(task);
        }
    }
}

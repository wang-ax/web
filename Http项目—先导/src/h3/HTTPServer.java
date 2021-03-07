package h3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HTTPServer {

    public static void main(String[] args) throws IOException {
        //固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true){
            Socket socket =serverSocket.accept();
            Runnable task = new RequestResponseTask1(socket);
            new Thread(task).start();
        }
    }
}

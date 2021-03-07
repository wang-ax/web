package h2;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class RequestResponseTask1 implements  Runnable{
    private  final  Socket socket;
    private  static final  String DOC_BASE="D:\\Bit\\code\\java-code\\20210304HTTP项目1\\docBase";

    public RequestResponseTask1(Socket socket) {
        this.socket = socket;
    }
    //提前定义好一个后缀名对应表
    // 文件的后缀名和contentType之间对应，使用Map进行保存
    private static  final Map<String,String> mimeTypeMap = new HashMap<>();
    static {
        mimeTypeMap.put("txt","text/plain");
        mimeTypeMap.put("html","text/html");
        mimeTypeMap.put("js","application/javascript");
        mimeTypeMap.put("jpg","image/jpeg");
    }


    @Override
    public void run() {
        try {
            System.out.println("一条TCP请求连接");

            //1.进行http请求的解析——不包含queryString
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream,"UTF-8");
            scanner.next();//读到请求方法
            String path = scanner.next();//读到请求的路径
            System.out.println(path);

            //一般的根路径后面什么都不带的时候，访问的就是index.html
            //要进行特殊处理
            if (path.equals("/")){
                //welcome-file
                path = "/index.html";
            }

            //要访问的文件的路径
            String filePath = DOC_BASE+path;//用户请求的静态资源对应的路径
            File resource = new File(filePath);
            if (resource.exists()){//判断文件是否存在
                //读取文件内容,写入response body中
                OutputStream outputStream = socket.getOutputStream();
                Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
                PrintWriter printWriter = new PrintWriter(writer);

                //通过文件名来确定contentType
                String contentType = "text/plain";//默认的contentType
                // 找到路径对应的后缀（字符串处理）
                if (path.contains(".")) {
                    int i = path.lastIndexOf(".");
                    String suffix = path.substring(i + 1);//得到.后面的字符串即后缀名
                    contentType = mimeTypeMap.getOrDefault(suffix, contentType);
                    //如果在后缀名表中没有找到，就使用默认的contentType
                }
                // 如果 contentType 是 "text/..."，代表是文本
                // 我们都把字符集统一设定成 utf-8格式的
                if (contentType.startsWith("text/")) {
                    contentType = contentType + "; charset=utf-8";
                }
                //响应的状态行
                printWriter.printf("HTTP/1.0 200 OK\r\n");
                //响应头信息
                printWriter.printf("Content-Type: %s\r\n", contentType);
                //空行，代表响应头结束
                printWriter.printf("\r\n");
                printWriter.flush();//flush一定不能忘！！！！

                //响应体——(响应正文)
                // 写入 response body
                try (InputStream resourceInputStream = new FileInputStream(resource)) {
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int len = resourceInputStream.read(buffer);//从文件中进行读取
                        if (len == -1) {//文件内容全部读完
                            break;
                        }
                        //结束读取文件，现在文件的内容全部在buffer中
                        outputStream.write(buffer, 0, len);//把buffer中内容写入outputStream
                    }
                    outputStream.flush();
                }
              //  printWriter.flush();  //不能忘记flush

            }else {
                //找不到要访问的资源，返回404
                //response：404
                OutputStream outputStream = socket.getOutputStream();
                Writer writer = new OutputStreamWriter(outputStream,"UTF-8");
                PrintWriter printWriter = new PrintWriter(writer);
                //响应：
                //写响应行
                printWriter.printf("HTTP/1.0 404 NotFound\r\n");
                //写响应头
                printWriter.printf("Content-Type:text/html;charset=utf-8\r\n");
                //空行,代表响应头结束
                printWriter.printf("\r\n");
                //响应体，html内容
                printWriter.printf("<h1>%s:对应资源不存在</h1>",path);
                //刷新，把数据写入TCP发送缓冲区
                printWriter.flush();
            }
        } catch (IOException  e) {
            //单次的请求响应周期错误，不会影响其他的
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

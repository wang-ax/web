package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName org.example.servlet
 * Description TODO
 * Author 30712
 * Date 2020-11-22
 * Time 16:16
 */
//注解的使用：小括号包裹多个属性，属性名等于属性值，多个之间逗号间隔
//(value = {"",""}，name = "")返回值是什么类型就写什么类型
//属性名为value可以缺省
//servlet 定义服务：服务路径必须是/开始，否则tomcat启动就会报错(尽量不要写特殊字符)
@WebServlet("/login301" )//路径重复会报错
public class Login301Servlet extends HttpServlet {
    //每次http请求映射到某个servlet的资源路径，都会调用service生命周期？？
    //如果请求方法没有重写，就会调用父类的doXXX（对应请求方法），返回405
    //如果重写，会将请求数据包装成一个Request请求对象，此时虽然还没有响应，但是也包装成了一个Response响应对象
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求，响应编码，及响应数据类型
        req.setCharacterEncoding("UTF-8");//设置请求体编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");//设置响应的数据类型

        //解析请求数据，通过req对象
        //获取数据，request.getParameter方法获取请求数据，url和请求体，数据格式为k1=v1&k2=v2
        String userName = req.getParameter("username");//通过键获取值，和前端要对应
        String passWord = req.getParameter("password");
        System.out.printf("==============用户名(%s) 密码 (%s) %n",userName,passWord);

        //模拟校验
        if("abc".equals(userName) && "123".equals(passWord)) {
            //页面跳转
            //重定向：http响应状态码设置为301，302，307，响应头Location
            resp.sendRedirect("home.html");
        }else if("abc".equals(userName)){
            //密码不对，只有用户名正确
            //转发,不跳转
            req.getRequestDispatcher("home.html").forward(req,resp);
        }else {
            //响应资源，返回响应数据
            PrintWriter pw = resp.getWriter();//response获取io输出流
            pw.println("登录失败");
            pw.println("<h3>用户名" + userName + "或密码错误</h3>");
            pw.flush();//有缓冲的IO操作，需要刷新缓冲区才会真正的发送数据
            pw.close();//关闭资源
        }
    }

}

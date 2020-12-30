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
 * Date 2020-12-29
 * Time 20:38
 */
@WebServlet("")
public class HomeServlet  extends HttpServlet {
    //private static int COUNT;//使用静态的肯定可以
    private int COUNT;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        COUNT++;
        req.setCharacterEncoding("utf-8");//设置请求的字符编码
        String name = req.getParameter("name");//获取http协议提交过来的数据，返回的结果是字符串类型的
        resp.setContentType("text/html;charset=utf-8");//设置响应体的类型
        PrintWriter writer = resp.getWriter();
        //定义一个模板
        String template ="<html>\n" +
                "    <head>\n" +
                "        <meta charset = \"utf-8\">\n" +
                "        <title>个人名片</title>\n" +
                "\n" +
                "        <link rel =\"stylesheet\" href =\"css/style.css\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <div>访问次数：:COUNT:</div>" +
                "        <h1>:name:的主页</h1>\n" +
                "        <div class =\"hobby\">\n" +
                "            <h2>:name:的爱好</h2>\n" +
                "            <ul>\n" +
                "                <li>追剧</li>\n" +
                "                <li>敲代码</li>\n" +
                "                <li>吃火锅</li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "\n" +
                "        <div>\n" +
                "            <h2>:name:的理想</h2>\n" +
                "            <ol>\n" +
                "                <li><a href=\"https://www.tencent.com/zh-cn\">去大厂</a></li>\n" +
                "                <li>拿高薪</li>\n" +
                "                <li>找到一个好工作</li>\n" +
                "            </ol>\n" +
                "        </div>\n" +
                "\n" +
                "        <img src=\"image/profile.jpg\" width = \"200\" height=\"200\">\n" +
                "    </body>\n" +
                "</html>";
        //只需要把占位符替代就可以了
         String content = template.replace(":name:",name).replace(":COUNT:",String.valueOf(COUNT));
         writer.println(content);
    }
}

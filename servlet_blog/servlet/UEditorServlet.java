package org.example.servlet;

import org.example.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;

/**
 * ClassName org.example.servlet
 * Description TODO 图片上传
 * 1.修改idea中tomcat配置的应用上下文路径，maven中的finalName
 * 2.修改webapp/static/ueditor.config.js
 * （应用上下文路径和服务路径）
 * 3.实现后端接口（和第二步的服务路径要一致）
 * 4.修改config.json配置
 * 5.
 * Author 30712
 * Date 2020-12-06
 * Time 16:34
 */
@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        URL url = UEditorServlet.class.getClassLoader().getResource("config.json");
        //URL获取到的都是编码后的字符串，需要使用时，先解码再使用
        String path = URLDecoder.decode(url.getPath(),"UTF-8");
        //框架提供的富文本编辑器的上传功能
        MyActionEnter enter = new MyActionEnter(req,path);
        String  exec = enter.exec();//执行
        //作为响应体给前端用
        PrintWriter ps = resp.getWriter();
        ps.println(exec);
        ps.flush();
        ps.close();
    }
}

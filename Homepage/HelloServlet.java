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
 * Time 20:06
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String actor = req.getParameter("actor");
       if (actor == null){
           actor = "Jia";
       }
       resp.setContentType("text/plain; charset = utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("actor=" + actor);
    }
}

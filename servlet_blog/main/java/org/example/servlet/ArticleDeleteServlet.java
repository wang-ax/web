package org.example.servlet;

/**
 * ClassName org.example.servlet
 * Description TODO 删除文章
 * Author 30712
 * Date 2020-12-06
 * Time 9:28
 */

import org.example.dao.ArticleDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends  AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String ids = req.getParameter("ids");
        int num = ArticleDAO.delete(ids.split(","));
        return null;
    }
}

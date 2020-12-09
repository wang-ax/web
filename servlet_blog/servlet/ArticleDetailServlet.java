package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName org.example.servlet
 * Description TODO
 * Author 30712
 * Date 2020-12-06
 * Time 11:07
 */
@WebServlet("/articleDetail")
public class ArticleDetailServlet  extends  AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String id = req.getParameter("id");
        Article a = ArticleDAO.query(Integer.parseInt(id));//通过文章id得到文章内容
        return a;//返回一个文章对象
    }
}

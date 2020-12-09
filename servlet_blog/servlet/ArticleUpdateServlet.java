package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * ClassName org.example.servlet
 * Description TODO
 * Author 30712
 * Date 2020-12-06
 * Time 11:48
 */
@WebServlet("/articleUpdate")
public class ArticleUpdateServlet  extends  AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        InputStream is = req.getInputStream();
        Article a = JSONUtil.deserialize(is,Article.class);
        int num = ArticleDAO.update(a);
        return null;
    }
}

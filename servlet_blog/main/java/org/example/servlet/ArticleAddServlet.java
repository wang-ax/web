package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

/**
 * ClassName org.example.servlet
 * Description TODO 添加文章
 * Author 30712
 * Date 2020-12-06
 * Time 10:26
 */
@WebServlet("/articleAdd")
public class ArticleAddServlet  extends  AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //从session中取用户
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");

        //请求数据类型是application/json,需要使用输入流获取
        InputStream is = req.getInputStream();
        //反序列化为Java对象（文章对象）
        Article a = JSONUtil.deserialize(is, Article.class);

        a.setUserId(user.getId());//需要设置userId
        int num = ArticleDAO.insert(a);
        return null;
    }
}

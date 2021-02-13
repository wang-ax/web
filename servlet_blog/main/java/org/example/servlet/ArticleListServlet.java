package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName org.example.servlet
 * Description TODO
 * Author 30712
 * Date 2020-12-03
 * Time 21:05
 */
@WebServlet("/articleList")
public class ArticleListServlet extends  AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //动态获取用户信息，通过session中保存的用户信息
        //获取session
        HttpSession session = req.getSession(false);
        //session会话为空
       /* if(session == null){
            throw  new AppException("ART002","用户没有登录，不允许访问");
        }*/

        //获取登录时创建的session保存的用户信息
        User user = (User) session.getAttribute("user");
        //有session但是user == null，用户需要重新登录
        /*if(user == null){
            throw  new AppException("ART002","会话异常，请重新登录");
        }*/
        //用户已经登录，并且保存了用户的信息
        //用List包裹文章列表，通过JDBC操作来获取（通过用户Id查询）
        List<Article> articles = ArticleDAO.queryByUserId(user.getId());
        return articles;
    }
}

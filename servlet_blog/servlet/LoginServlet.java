package org.example.servlet;

import org.example.dao.LoginDAO;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName org.example.servlet
 * Description TODO
 * Author 30712
 * Date 2020-11-29
 * Time 16:33
 */
@WebServlet("/login")
public class LoginServlet  extends  AbstractBaseServlet {
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //解析请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = LoginDAO.query(username);//通过LoginDAO，使用username进行查询，返回一个user
        if(user == null){
            throw new AppException("LOG002","用户不存在");
        }
        if(!user.getPassword().equals(password)){
            throw new AppException("LOG003","用户名或密码错误");
        }
        //登录成功，创建session
        HttpSession session = req.getSession();
        session.setAttribute("user",user);//把查到的这个用户设置进去
        return null;

        /*if("abc".equals(username)) {//模拟用户登录
            //重定向
            return  null;//登录成功不需要跳转
        }else {
            //登录错误LOG
            throw new AppException("LOG001","用户名，密码错误");//校验不成功，在父类中处理异常
        }*/

    }
}

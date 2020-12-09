package org.example.servlet;

import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName org.example.servlet
 * Description TODO 定义模板方法 ，父类定义好逻辑，定义好子类可以重写的方法，可以实现代码的复用
 * Author 30712
 * Date 2020-11-29
 * Time 16:07
 */
//使用抽象类，抽象的统一的基类；
//自己的子类只需要继承AbstractBaseServlet 就可以了
public  abstract   class AbstractBaseServlet  extends HttpServlet {
    @Override
    //protected 子类可以使用
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//设置请求体的编码格式
        resp.setCharacterEncoding("UTF-8");//设置响应体的编码
        resp.setContentType("application/json");//设置响应体的数据类型，浏览器要根据什么方式执行
        //resp.setContentType("text/html");

        //Session 会话管理，注册和登录，不需要登录后进行，其他都需要登陆后访问
        //req.req.getServletPath()获取服务路径
        //TODO

        JSONResponse json = new JSONResponse();//返回给前端一个约定好的json格式
        try{
            //调用子类重写方法
            Object data = process(req,resp);
            //子类的process方法执行完没有抛异常，表示业务成功
            json.setSuccess(true);//业务成功，只需要返回true和对应的data
            json.setData(data);
        }catch (Exception e) {
            //异常处理；JDBC异常（SQLException），JSON处理的异常（序列化和反序列化异常）
            e.printStackTrace();//打印异常
            // 自定义异常返回错误消息
            //json.setSuccess(false)不需要设置，new的时候就是false
            String code = "UNKNOWN";
            String s = "未知错误";
            if(e instanceof AppException) {
                code = ((AppException) e).getCode();
                s = e.getMessage();
            }
            json.setCode(code);//设置code和message
            json.setMessage(s);
        }
        PrintWriter pw = resp.getWriter();
        //把pw对象序列化成json字符串
        pw.println(JSONUtil.serialize(json));//json字符串序列化打印出来
        pw.flush();
        pw.close();
    }
    protected  abstract  Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}

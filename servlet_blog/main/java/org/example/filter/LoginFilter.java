package org.example.filter;

import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName org.example.filter
 * Description TODO
 * Author 30712
 * Date 2020-12-06
 * Time 15:08
 */

/**
 * 服务端资源：/login不用校验session。其他都要校验，如果不通过，返回401，响应内容随意
 * 前端资源：/jsp检验session，不通过重定向到登录页面
 *          /js，/static/，/view/
 */
//配置用户统一会话管理的过滤器，会匹配所有的请求路径
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    /**
     * 每次http请求匹配到过滤器路径时，会执行该过滤器的doFilter
     * 如果往下执行，调用filterChain.doFilter(request,response)
     * 否则自行处理响应
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        //不需要登录允许访问；继续调用，往下执行
        if(servletPath.startsWith("/js/") || servletPath.startsWith("/static/")
            || servletPath.startsWith("/view/") || servletPath.equals("/login")){
            chain.doFilter(request,response);
        }else {
            //获取session对象
            HttpSession session = req.getSession(false);
            //没有登录，需要验证。如果没有登录，还需要根据前端或后端做不同的处理
            if(session == null || session.getAttribute("user") == null){
                //前端重定向到登录页面
                if (servletPath.startsWith("/jsp/")){
                    resp.sendRedirect(basePath(req)+"/view/login.html");
                }else{//后端返回401状态码
                    //返回统一的数据格式
                    resp.setStatus(401);
                    req.setCharacterEncoding("UTF-8");//设置请求体的编码格式
                    resp.setCharacterEncoding("UTF-8");//设置响应体的编码
                   resp.setContentType("application/json");
                    JSONResponse json = new JSONResponse();
                    json.setCode("LOG000");
                    json.setMessage("用户没有登录，不允许访问");
                    PrintWriter pw = resp.getWriter();
                    pw.println(JSONUtil.serialize(json));
                    pw.flush();
                    pw.close();
                }
            }else {
                chain.doFilter(request,response);//敏感资源，但是已经登录，允许继续执行
            }
        }

    }

    /**
     * 根据http请求，动态的获取访问路径（服务路径之前的部分）
     * @param req
     * @return
     */
    public static  String basePath (HttpServletRequest req){
        String schema = req.getScheme();//http
        String host = req.getServerName();//主机ip或者域名
        int port = req.getServerPort();//服务器端口号
        String contextPath = req.getContextPath();//应用上下文路径
        return schema+ "://" + host +":"+port + contextPath;
    }

    @Override
    public void destroy() {

    }

}

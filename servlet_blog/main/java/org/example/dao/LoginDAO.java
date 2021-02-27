package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * ClassName org.example.dao
 * Description TODO  登录，通过用户账号查询用户，要产生出一个实体类
 * 将数据库表转换成一个实体类，最终得到一个user
 * Author 30712
 * Date 2020-12-03
 * Time 19:25
 */
public class LoginDAO {
    public static User query(String username) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection(); //1.建立数据库连接
            String sql = "select  id,username,password,nickname,sex,birthday,head " +
                    " from user " +
                    " where  username=?";
            ps = c.prepareStatement(sql);//创建操作命令对象
            ps.setString(1,username);//设置占位符
            rs = ps.executeQuery();//执行sql之后得到的结果集，使用无参的
            //处理结果集
            User user = null;//先定义成null

            while (rs.next()){
                user = new User();//如果查询后有结果集，再new一个User对象
                //设置User值
                user.setId(rs.getInt("id"));//通过字段名得到值，并设置到user对象中去
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setSex(rs.getBoolean("sex"));
                //日期的使用,java中一般使用java.util.Date(年月日时分秒)
                //rs.getDate()返回值是java.sql.Date(时分秒)
                //rs.getTimeStamp()返回值是java.sql.Timestamp(年月日时分秒)
                //rs.getDate("birthday").getTime()
                java.sql.Date  birthday= rs.getDate("birthday");
                if(birthday != null) {
                    user.setBirthday(new Date(birthday.getTime()));
                }
                user.setHead(rs.getString("head"));
            }
            return user;
        }catch (Exception e){
            throw  new AppException("LOG001","查询用户操作出错",e);
            //上面自己写的的代码的问题，在这里就会抛出异常信息
            //出现错误，查询操作日志以及堆栈信息，定位代码出错的位置
        }finally {
            DBUtil.close(c,ps,rs);//关闭连接、释放资源
        }
    }
}

package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ClassName org.example.util
 * Description TODO  工具类
 * Author 30712
 * Date 2020-11-29
 * Time 16:36
 */
//数据库的连接获取
public class DBUtil {
   //private static  final String URL="jdbc:mysql://localhost:3306/my_servlet_blog?user=root&password=123456&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static  final String URL="jdbc:mysql://localhost:3306/my_servlet_blog?user=root&password=WXZ0120&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static  final DataSource DS = new MysqlDataSource();//final修饰，引用不能改变，对象可以改变

    /**
     * 工具类提供数据库JDBC操作
     * 缺点：1.static代码块出现错误，NoClassDefFoundError 表示类可以找到但是类加载失败，
     *        ClassNotFoundException ：找不到类
     *      2.多线程学习之后：采取双重校验锁的单例模式来创建DataSource
     *      3.工具类设计上不是最优的、数据库框架ORM框架Mybatis，都是模板模式设计的
     */
    static {
        ((MysqlDataSource) DS ).setUrl(URL);
    }
    public static Connection getConnection(){
        try {
            return  DS.getConnection();
        } catch (SQLException e) {
            //throwables.printStackTrace();
            //抛自定义异常
            //return null;
            //e.printStackTrace();
            throw new AppException("DB001","获取数据库连接失败",e);
        }
    }
    //关闭数据库连接、释放资源，使用重载（因为如果是查询操作，就会有查询出来的结果集，结果集的资源也需要释放）
    //重载
    public static  void close(Connection c , Statement s){
        close(c,s,null);
    }
    public static  void close(Connection c , Statement s, ResultSet r){
        //反向释放资源
        try {
            if(r != null){
                r.close();
            }
            if(s != null){
                s.close();
            }
            if(c != null){
                c.close();
            }
        } catch (SQLException e) {
            throw new AppException("DB002","数据库释放资源出错",e);
        }
    }
}

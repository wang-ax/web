package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName org.example.dao
 * Description TODO 结合数据库的操作
 * Author 30712
 * Date 2020-12-03
 * Time 21:05
 */
public class ArticleDAO {
    public static List<Article> queryByUserId(Integer userId) {//传入userId来来查询该用户的文章信息
        List<Article> articles = new ArrayList<>();//一个用户会有多个文章，所以要用List来包裹，其中是Article对象
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            c = DBUtil.getConnection();
            String sql = "select  id, title from article where  user_id =?";
            ps = c.prepareStatement(sql);//预编译
            //设置占位符
            ps.setInt(1,userId);
            rs = ps.executeQuery();//执行sql
            while (rs.next()){
                //处理结果集
                Article a = new Article();
                //从结果集取值设置到文章对象
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                articles.add(a);
            }
            return articles;
        }catch (Exception e) {
            throw new AppException("ART001","查询文章列表出错",e);//自己代码出现的错误在这里捕获异常,检查堆栈信息
        }finally {
            DBUtil.close(c,ps,rs);
        }
    }

    public static int delete(String[] split) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DBUtil.getConnection();
            StringBuilder sql= new StringBuilder("delete  from article where  id in (");//拼字符串，根据数组中的内容
            //????
            for (int i =0;i<split.length;i++){
                if(i != 0)
                    sql.append(",");
                sql.append("?");
            }
            sql.append(")");
            //先插入? 再插入 ,? ,?

            ps = c.prepareStatement(sql.toString());
            //设置占位符
            for (int i = 0;i<split.length;i++){
                //占位符设置是从1开始，所以需要i+1
                ps.setInt(i+1,Integer.parseInt(split[i]));
            }

            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("ART004","文章删除操作出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static int insert(Article a) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            String sql = "insert  into article(title,content,user_id ) values (?,?,?)";
            ps = c.prepareStatement(sql);
            //替换占位符
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getUserId());
            return ps.executeUpdate();
        }catch (Exception e){
            throw  new AppException("ART005","新增文章操作出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static Article query(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select id,title,content from article where id=?";
            ps = c.prepareStatement(sql);
            //替换占位符
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Article a = null;
           //处理结果集对象
            while (rs.next()){
                a  = new Article();
                //根据结果集设置文章属性
                a.setId(id);
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
            }
            return a;
        }catch (Exception e){
            throw new AppException("ART006","查询文章详情出错",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
    }

    public static int update(Article a) {
        Connection c =null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            String sql = "update article set title=?,content=? where id=?";
            ps  = c.prepareStatement(sql);
            //设置占位符
            //通过文章对象来获取tile，content，id；然后将获取的值用来替换前面占位符的?
            ps.setString(1,a.getTitle());
            ps.setString(2,a.getContent());
            ps.setInt(3,a.getId());
            return ps.executeUpdate();
        }catch(Exception e){
            throw  new AppException("ART007","修改文章操作出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }
}

package org.example.dao;

import org.example.model.Article;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName org.example.dao
 * Description TODO
 * Author 30712
 * Date 2021-06-24
 * Time 9:46
 */
public class ArticleDAOTest {

    @Test
    public void queryByUserId() {
        ArticleDAO articleDAO = new ArticleDAO();
        List<Article> list  = new ArrayList<>();
        list = articleDAO.queryByUserId(1);
        for (Article article : list){
            System.out.println(article.getTitle());
        }
    }

    @Test
    public void delete() {
        ArticleDAO articleDAO = new ArticleDAO();
        String[] strings = {"2","13"};
        int res = articleDAO.delete(strings);
        Assert.assertEquals(res,2);
    }

    @Test
    public void insert() {
        ArticleDAO articleDAO = new ArticleDAO();
        Article article = new Article();//创建一个文章实例
        article.setTitle("单元测试");
        article.setContent("进行单元测试使用的框架是Junit框架");
        article.setUserId(1);
        articleDAO.insert(article);//插入
    }

    @Test
    public void query() {
        ArticleDAO articleDAO = new ArticleDAO();
        /**
         * 如果根据文章id查询文章详情，数据库中没有该文章
         * getTitle 就会出现NullPointerException
         */
        /*Article article = articleDAO.query(1);
        System.out.println(article.getTitle());*/
        Article article = articleDAO.query(12);
        System.out.println(article.getTitle());
        System.out.println(article.getContent());
        System.out.println(article);//Article(id=12, title=单元测试, content=进行单元测试使用的框架是Junit框架, createTime=null, viewCount=null, userId=null)
        //根据ArticleDAO中写的query方法，在查询之后处理结果集的时候，只设置了id，title，content
    }

    @Test
    public void update() {
        ArticleDAO articleDAO = new ArticleDAO();
        Article article = new Article();
        article.setId(13);
        article.setTitle("Hello");
        article.setContent("public" +
                "static" +
                "void main");
        articleDAO.update(article);
    }
}
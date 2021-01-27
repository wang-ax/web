package org.example.model;

/**
 * ClassName org.example.model
 * Description TODO
 * Author 30712
 * Date 2021-01-25
 * Time 9:08
 */

import java.util.Objects;

/**
 * 每一个本地的html文件对应一个文档对象，包含下列的属性
 */
public class DocInfo {
    private  Integer id;//类似数据库中的主键，识别不同的文档
    private String title;//标题：html的文件名作为标题
    private String url;//oracle官网api文档下html的url
    private  String content;//网页正文：html<标签>内容</标签>，内容为正文


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocInfo docInfo = (DocInfo) o;
        return Objects.equals(id, docInfo.id);//只要id相同，equals方法就会返回true
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, url, content);
    }
}

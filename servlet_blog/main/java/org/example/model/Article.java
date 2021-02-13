package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * ClassName org.example.model
 * Description TODO
 * Author 30712
 * Date 2020-12-03
 * Time 19:32
 */
@Getter
@Setter
@ToString
public class Article {
    private  Integer id;
    private  String title;
    private  String  content;
    private Date createTime;
    private  Integer viewCount;
    private Integer userId;
}

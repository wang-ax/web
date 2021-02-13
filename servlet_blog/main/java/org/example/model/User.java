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
 * Time 19:28
 */
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private  String username;
    private  String password;
    private  String nickname;
    private Boolean sex;
    private Date birthday;//要使用java.util.Date
    private String head;
}


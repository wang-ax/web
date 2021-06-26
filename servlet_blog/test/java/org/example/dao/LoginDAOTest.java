package org.example.dao;

import org.example.model.User;
import org.junit.Test;

/**
 * ClassName org.example.dao
 * Description TODO
 * Author 30712
 * Date 2021-06-25
 * Time 16:56
 */
public class LoginDAOTest {
    @Test
    public void query() {
        LoginDAO loginDAO = new LoginDAO();
/*        User user = loginDAO.query("a");
        System.out.println(user);*/

        User user1 = loginDAO.query("d");
        //System.out.println(user1.getPassword());//空指针异常
        System.out.println(user1);
    }
}
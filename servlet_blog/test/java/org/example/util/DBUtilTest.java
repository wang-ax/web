package org.example.util;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * ClassName org.example.util
 * Description TODO
 * Author 30712
 * Date 2020-11-29
 * Time 16:47
 */
public class DBUtilTest {
    @Test
    public void testDBUtil() {
        //DBUtil dbUtil = new DBUtil();
        Connection connection = DBUtil.getConnection();
        //判断是不是为空
       // System.out.println(connection);
        Assert.assertNotNull(connection);
    }
}

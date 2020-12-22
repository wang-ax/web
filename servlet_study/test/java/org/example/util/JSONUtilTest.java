package org.example.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName org.example.util
 * Description TODO 单元测试的代码，工具类的测试代码
 * Author 30712
 * Date 2020-11-29
 * Time 10:43
 */
public class JSONUtilTest {
    //单元测试：junit框架使用方法上的@Test注解,保证方法为public void
    @Test
    public  void  testSerialize() {
        //测试序列化操作，使用map进行模拟复杂对象
        Map map = new HashMap();
        map.put("name","40班");//map中放入内容，班级对象
        map.put("student", new int[]{1,2});//学生对象
        String json = JSONUtil.serialize(map);//把Java对象序列化成一个json对象
        System.out.println(json);
        Assert.assertNotNull(json);//断言它是一个不为空的
    }
//反序列化的代码
    @Test
    public  void  testDeSerialize() {
        //类加载器加载某个资源，返回输入流
        InputStream is = JSONUtilTest.class.getClassLoader()
                .getResourceAsStream("login.json");
        //把这个文件中的内容反序列化为Java对象
        Map map = JSONUtil.deserialize(is,HashMap.class);
        System.out.println(map);
        Assert.assertNotNull(map);


    }
}

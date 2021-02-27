package org.example.test;

import org.junit.Assert;
import org.junit.Test;

public class StringInMemory {

    // true：都是常量池中的字面量
    @Test
    public void test1(){// 测试通过
        String s1 = "hello";
        String s2 = "hello";
        Assert.assertTrue(s1 == s2);//在常量池
    }

    // true：s3 = "he" + "llo"
    // JVM在编译期间会进行优化：s3为字面量拼接的字符串"hello"，存在常量池
    @Test
    public void test2(){// 测试通过
        String s1 = "hello";
        String s2 = "hel" + "lo";//一个字符串，在Java文件编译成class文件的时候，+ 自动做一些操作，直接变化一个字符串
        //是在编译期间进行了优化
        Assert.assertTrue(s1 == s2);
    }

    // s2创建了以下对象
    // 1."hello"：存在字符串常量池，如果常量池已有"hello"就不创建
    // 2.new String("hello")：存在堆中
    // s1为常量池的"hello"对象，s2为堆中的对象new String("hello")
    @Test
    public void test3(){// 测试不能通过
        String s1 = "hello";
        String s2 = new String("hello"); //new 了一个对象
        Assert.assertTrue(s1 == s2);
    }

    // 调用intern方法时，如果池已经包含与equals(Object)方法确定的
    // 相当于此String对象的字符串，则返回来自池的字符串。 否则，此
    // String对象将添加到池中，并返回对此String对象的引用。
    // 由此可见，对于任何两个字符串s和t，s.intern() == t.intern()
    // 是true当且仅当s.equals(t)是true。
    @Test
    public void test4(){// 测试通过
        String s1 = "hello";
        String s2 = new String("hello");
        Assert.assertTrue(s1 == s2.intern());//两个引用指向的对象是相同的
        //由于常量池中已经有hello这个字符串，所以调用s2.intern()返回的就是指向hello这个字符串的引用
    }

    // s4是用s2和s3两个常量池中的对象相加新生成的对象，存在堆中
    @Test
    public void test5(){// 测试不能通过
        String s1 = "hello";
        String s2 = "hel";
        String s3 = "lo";
        String s4 = s2 + s3;
        //字符串相加，底层是StringBuilder().append().toString()
        Assert.assertTrue(s1 == s4);
    }

    /**
     * 常量池：计算机、软件
     * 堆：new String("计算机软件")
     * "计算机软件"会在常量池中创建字符串对象并返回引用
     */
    @Test
    public void test6(){//false
        String s = new StringBuilder("计算机").append("软件").toString();
        //
        Assert.assertTrue(s == "计算机软件");//false
        //s是在堆上的，“计算机软件”是在常量池
    }

    /**
     * 常量池：计算机、软件
     * 堆：new String("计算机软件")
     * 调用s.intern()---> 常量池生成字符串引用，指向堆中的s，并返回该引用
     * 之后的"计算机软件"字符串，都是从常量池中获取，但是指向堆中的s对象
     */
    @Test
    public void test7(){//true
        String s = new StringBuilder("计算机").append("软件").toString();
        // s.intern()在字符串常量池生成一个引用，指向s，"计算机软件"这个字符串是s
        Assert.assertTrue(s.intern() == "计算机软件");
        Assert.assertTrue(s.intern() == s);//指向的同一个对象，都指向的是堆里面的对象
        Assert.assertTrue(s == "计算机软件");
    }
    @Test
    public void test7_1() {//false
        String s = new StringBuilder("计算机").append("软件").toString();
        // 先执行"计算机软件"，会在字符串常量池生成一个字符串对象，之后intern返回是字符串对象的引用
        Assert.assertTrue("计算机软件" == s.intern());//true
        Assert.assertTrue(s.intern() == s);//false
        //s.intern() 常量池中的对象    s  堆中的对象
        Assert.assertTrue(s == "计算机软件");//false
    }


    /**
     * 常量池：计算机软件
     * 堆：new String("计算机软件")--->指向常量池的字符串
     * s.intern()--->返回常量池字符串对象的引用
     */
    @Test
    public void test8(){
        String s = new StringBuilder("计算机软件").toString();
        Assert.assertTrue(s.intern() == "计算机软件");//true
        Assert.assertTrue(s.intern() == s);//false
    }

    /**
     * jvm在初始化时，会默认加载一些资源，这些资源中包含一些字符串，
     * 如"java"，会加载到常量池。
     * s.intern()返回常量池中的字符串对象
     * s为堆中的new String("java")
     */
    @Test
    public void test111(){
        String s = new StringBuilder("ja").append("va").toString();
        Assert.assertTrue(s.intern() == s);

    }
    @Test
    public void test111_2(){
        String s = new StringBuilder("计算机").append("软件").toString();
        // s.intern()在字符串常量池生成一个引用，指向s，"计算机软件"这个字符串是s
        Assert.assertTrue(s.intern() == s);
    }
}

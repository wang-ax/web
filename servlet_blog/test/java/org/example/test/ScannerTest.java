package org.example.test;

import java.util.Scanner;

/**
 * ClassName org.example.test
 * Description TODO
 * Author 30712
 * Date 2020-12-08
 * Time 18:49
 */

/**
 * 1.
 * 第一行输入一个整型数字N
 * 第二行输入N个整数，空格间隔
 *
 * 2.满足多个测试用例，使用while循环
 * 每一组的测试用例都是在循环中的
 *
 */

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);//System.in是输入流

        while (scanner.hasNext()) {//阻塞式方法
            int N = scanner.nextInt();//输入整数n
            //换行符会让输入流接收数据，nextInt是按照换行符\n或者空格间隔
            for (int i =0;i<N;i++){
                //接收题目要求的输入
                int next = scanner.nextInt();
                //题目要求的业务逻辑
                //输出题目要求的内容
                System.out.println(next);
            }
        }
    }
}

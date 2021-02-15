package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName org.example.util
 * Description TODO 序列化和反序列化
 * 工具类，会涉及到类和对象的设计 ，提供给其他地方用
 * Author 30712
 * Date 2020-11-29
 * Time 9:28
 */

public class JSONUtil {
    private static  final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * java对象序列化为json字符串
     * @param o Java对象
     * @return  json字符串
     */
    public static  String serialize(Object o) {
        try {
            return MAPPER.writeValueAsString(o);//把对象写成字符串输入出来
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            //要保证有返回值
            //明确返回一个字符串，或者自己再抛一个异常
            throw  new RuntimeException("json序列化失败："+o);
        }
    }

    /**
     * 反序列化，将输入流反序列化为Java对象
     * @param is 输入流（获取的是请求体的输入流）
     * @param clazz 要反序列化的类型
     * @param <T>
     * @return 反序列化对象
     */
    //使用泛型，最终得到一个得到一个明确的类型
    public static <T> T deserialize(InputStream is , Class<T> clazz){
        try {
            return MAPPER.readValue(is,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw  new RuntimeException("json反序列化失败："+clazz.getName());
        }
    }
}

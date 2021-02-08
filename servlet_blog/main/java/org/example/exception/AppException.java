package org.example.exception;

/**
 * ClassName org.example.exception
 * Description TODO
 * Author 30712
 * Date 2020-11-29
 * Time 17:11
 */

/**
 * 自定义异常类：业务异常抛自定义异常或其他异常
 * 自定义异常返回给定的错误码，其他异常返回其他错误码
 */
public class AppException extends RuntimeException{
    //给前端返回的json字符串中，保存错误码
    private  String  code;

    public AppException(String code,String message) {
        super(message);
        this.code = code;
        //this(code,message,null);//合并上面的两行代码
    }

    public AppException(String code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
    //需要设置错误码
    public String getCode() {
        return code;
    }
}

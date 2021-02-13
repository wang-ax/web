package org.example.model;

/**
 * ClassName org.example.model
 * Description TODO
 * Author 30712
 * Date 2020-12-01
 * Time 19:34
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * http响应json数据，前后端统一约定的json格式
 * 响应状态码都是200，进入ajax的success来使用
 * 操作成功：{success:true,data:XXX}
 * {success:false ,code:XXX,message:XXX}
 */
@Setter
@Getter
@ToString
public class JSONResponse {
    //业务操作是否成功
    private boolean success;
    //业务操作的消息码，一般，出现错误时错误码才有意义，开发人员就可以定位问题
    private String code;
    //业务操作的错误消息，给用户看的信息
    private String message;
    //业务数据，业务操作成功时，给前端ajax success 方法使用，解析响应json数据，渲染网页内的
    private Object data;


}

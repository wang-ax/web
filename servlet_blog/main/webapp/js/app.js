//ajax请求
$(function () {//页面加载之后执行function里面的逻辑
    //jquery中使用$("#id")通过元素id获取某个页面元素
    $("#login_form").submit(function () {//表单的提交不使用默认的提交方式，自己发请求
        //ajax自己发请求
        $.ajax({
            url:"../login",//请求的服务路径
            type:"POST",//请求方法
            //contentType:"",//请求的数据类型:请求头Content-Type，默认表单格式
            data:$("#login_form").serialize(),//请求数据：序列化表单的数据
            dataType:"json",//响应的数据类型，不使用默认的表单提交格式，json需要手动指定
            success:function (r) {//响应体json字符串，会解析为方法参数
                // alert(JSON.stringify(r))
                if(r.success){
                    //前端去跳转,前端URL直接跳转到某个路径
                    window.location.href="../jsp/articleList.jsp";
                }else{
                    alert("错误码:"+r.code +"\n错误信息:"+r.message);//弹出提示框
                }
            }
        })
        //统一不执行默认的表单提交
        return false;//将原来的表单提交拒绝了，点击登录就不会进行表单提交了
    })
})
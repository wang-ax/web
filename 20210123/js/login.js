function login(form) {
    //校验用户名、密码，通过——表单提交，不通过——提示，不提交
    if (form.oninvalid){//校验不通过，提示并不提交
        //oninvalid当元素无效时运行脚本
        alert("用户名为空")
        return false;
    }
   //如果通过、表单提交

    //发起ajax请求代码
    // var xhr = new XMLHttpRequest();
    //
    // xhr.onload = function () {
    //     alert(xhr.responseText);
    //     //document.getElementById("demo").innerHTML = xhr.responseText;
    // }
    //
    // xhr.onerror = function () {
    //     alert("请求出错！")
    //    // document.getElementById("demo").innerHTML="请求出错";
    // }
    //
    //
    // //发送异步请求
    // xhr.open("POST","../data/login.ok.json",true);
    // xhr.send();

    /**
     * Ajax请求：一定要抓包查看内容
     * 1.请求：url，method，data（请求数据），Content-Type（请求数据类型）
     * 2.响应：状态码status，Content-Type（响应数据类型），data（响应体数据）
     */
    $.ajax({
        url:"../data/login.ok.json",//静态json文件模拟Servlet返回
        //url:"../data/login.error.json",//静态json文件模拟Servlet返回
        type:"POST",
        success:function (r) {//r={ok：true}
            if (r.ok){
                //登录操作，用户名、密码校验通过
                alert("登录成功，跳转页面")
                //$("#login_error").hide();
                //相对路径：是以引入js的html文件作为相对路径的参照点
                window.location.href="main.html";
            }else {//r={ok:false,code:xxx,message:xxx}
                //alert("错误码"+r.code+"\n错误信息"+r.message)
                $("#login_error").html(r.message);
                $("#login_error").show();
            }
        }
    })
    return  false;
}
//使用jquery，由事件驱动的，由某一个事件发生的某一个方法
//当html的文档全部被加载成功时，才会执行
$(function main(){
    //找到id是content的元素
    //当这个元素发生了mouseover这个事件时
    //执行我们传入的方法
    //把id 是content的元素的内容替换成“我是一名教师 ”
    $("#content").on("mouseover",function () {
        console.log("hello");
        $("#content").html("我是一名教师");
    });

    $("#content").on("mouseout",function () {
        $("#content").html("我是一名学生");
    });
});

/*
window.onload = function(){
    document.getElementById("button").onclick = function(){
        console.log("点击成功");
        document.getElementById("content").innerHTML="我是一名教师";
    };
};*/

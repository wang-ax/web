$(function main() {
    //实现一个动态的变换
    //setInterval（函数，1000）每1秒执行传入的函数——变换颜色
    setInterval(function () {
        let s1 = $(".s1");
        let s2 = $(".s2");
        s1.removeClass("s1").addClass("s2");
        s2.removeClass("s2").addClass("s1");
    },1000);
})
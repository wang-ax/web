$(function main() {
    $("button").on("click",function () {
        let e = $("h1");
        e.html("老师！");

        setTimeout(function () {

            e.html("学生！");
        },3000)
        //三秒之后只执行这一次动作
        //三秒之后再改原来的内容

    });
})
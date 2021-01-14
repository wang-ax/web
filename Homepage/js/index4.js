$(function main() {
    //每1秒，执行一次方法
    setInterval(function () {
        let ball = $(".ball");
        let top = parseInt(ball.css("top").slice(0,-2));
        let left = parseInt(ball.css("left").slice(0,-2));
        top += 10;
        left += 10;
        ball.css("top",top+"px");
        ball.css("left",left+"px");
    },1000/24);
})
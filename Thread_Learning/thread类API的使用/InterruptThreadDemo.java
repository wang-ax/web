package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 13:29
 */
public class InterruptThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               /* try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("以异常的形式退出");//如果以异常的形式退出，就会清除中断标志位，变为false
                }*/
                for (int i = 0; i < 20; i++) {
                   // System.out.println(Thread.currentThread().isInterrupted());//调用isInterrupted(),不会清楚中断标志位
                    System.out.println(Thread.interrupted());//清除中断标志位
                }
            }
        });
        thread.start();
        thread.interrupt();//中断标志位变为true
    }
}

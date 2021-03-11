package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 12:12
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t");
            }
        });
        t.start();
        t.join();
        //当前线程——main线程无条件等待，直到t线程执行完毕之后，当前线程再继续往后执行
        System.out.println("main");
    }
}

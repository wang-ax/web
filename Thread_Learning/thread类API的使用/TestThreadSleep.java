package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 11:54
 */
public class TestThreadSleep {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final  int n =i;
            //子线程休眠3秒钟，同时执行
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        System.out.println(n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        //main线程和子线程同时执行
        System.out.println("OK");
    }
}

package createThread;

/**
 * ClassName createThread
 * Description TODO
 * Author 30712
 * Date 2021-03-06
 * Time 13:31
 */
//创建线程的方式2：实现Runnable接口
public class TestThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学java多线程");
        }
    }

    public static void main(String[] args) {
        //创建Runnable解耦的实现类对象
        TestThread2 testThread2 = new TestThread2();

        //创建线程对象，通过线程对象来启动线程
        /* Thread thread = new Thread(testThread2);
        thread.start();*/
        new Thread(testThread2).start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习");
        }
    }

}

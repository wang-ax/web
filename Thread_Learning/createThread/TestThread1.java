package createThread;

/**
 * ClassName createThread
 * Description TODO
 * Author 30712
 * Date 2021-03-06
 * Time 13:01
 */
//创建线程的方式1：继承Thread类，重写run方法，调用start方法开启线程
public class TestThread1 extends  Thread{
    @Override
    public void run() {
        //run方法体线程
        for (int i = 0; i < 20; i++) {
            System.out.println("我在学java多线程");
        }
    }

    public static void main(String[] args) {
        //main线程，主线程
        //创建线程对象
        TestThread1 testThread1 = new TestThread1();
        //调用start()方法开启线程
        testThread1.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("我在学习");
        }
    }
}

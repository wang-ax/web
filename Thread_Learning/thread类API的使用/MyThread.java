package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 11:03
 */
public class MyThread implements  Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        Thread thread1 = new Thread(myThread,"学习");//thread1线程
        Thread thread2 = new Thread(myThread,"努力学习");//thread2线程
        thread1.setName("你好");
        thread2.setName("多线程");
        thread1.start();
        thread2.start();;
        System.out.println(Thread.currentThread().getName());//main
    }
}

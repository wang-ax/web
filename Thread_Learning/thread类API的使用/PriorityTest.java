package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 11:32
 */
public class PriorityTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"——>"+Thread.currentThread().getPriority());
        Thread t1 = new Thread(new MyPriority());
        Thread t2 = new Thread(new MyPriority());
        Thread t3 = new Thread(new MyPriority());
        Thread t4 = new Thread(new MyPriority());
        //设置优先级再启动
        t1.start();
        t2.setPriority(1);
        t2.start();

        t3.setPriority(3);
        t3.start();

        t4.setPriority(Thread.MAX_PRIORITY);
        t4.start();
    }
}
class  MyPriority implements  Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"——>"+Thread.currentThread().getPriority());
    }
}

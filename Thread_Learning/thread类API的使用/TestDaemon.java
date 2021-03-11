package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 11:21
 */
public class TestDaemon {
    public static void main(String[] args) {
        First first = new First();
        Second second = new Second();

        Thread thread1 = new Thread(first);
        thread1.setDaemon(true);//设置为守护线程
        thread1.start();

        new Thread(second).start();
    }

}
class First implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("你一直在学习");
        }
    }
}
class  Second implements  Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("你在学习Java");
        }
    }
}

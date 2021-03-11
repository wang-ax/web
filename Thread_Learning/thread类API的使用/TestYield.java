package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 12:03
 */
public class TestYield {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final  int n =i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(n);
                }
            });
        }
        for (Thread t :threads){
            t.start();
        }
        while (Thread.activeCount()>2){//当前活跃的线程数量只要大于2，就让当前线程让步
            Thread.yield();//让当前线程让步
        }
        System.out.println("OK");
    }
}

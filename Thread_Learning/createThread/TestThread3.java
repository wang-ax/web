package createThread;

import java.util.concurrent.*;

/**
 * ClassName createThread
 * Description TODO
 * Author 30712
 * Date 2021-03-06
 * Time 14:01
 */
//创建线程方法3：实现Callable接口
//需要返回值类型
//需要抛出异常
public class TestThread3 implements Callable {
    @Override
    public Boolean call() throws Exception {
        System.out.println("我正在学习");
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread3 t1 = new TestThread3();
        TestThread3 t2 = new TestThread3();
        TestThread3 t3 = new TestThread3();
        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> result1 = ser.submit(t1);
        Future<Boolean> result2 = ser.submit(t2);
        Future<Boolean> result3= ser.submit(t3);
        //获取结果
        boolean r1 = result1.get();
        boolean r2 = result2.get();
        boolean r3 = result3.get();

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        //关闭服务
        ser.shutdownNow();
    }
}

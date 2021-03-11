package createThread;

/**
 * ClassName createThread
 * Description TODO
 * Author 30712
 * Date 2021-03-06
 * Time 14:17
 */
public class TestThread4 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("使用匿名类创建Thread子类对象");
            }
        };

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用匿名类创建Runnable子类对象");
            }
        });

        Thread thread3 = new Thread(()-> System.out.println("使用lambda表达式创建Thread子类对象"));
        Thread thread4 = new Thread(()->{
            System.out.println("使用lambda表达式创建Runnable子类对象");
        } );
    }
}

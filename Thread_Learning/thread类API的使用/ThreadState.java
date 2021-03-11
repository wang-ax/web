package thread类API的使用;

/**
 * ClassName thread类API的使用
 * Description TODO
 * Author 30712
 * Date 2021-03-11
 * Time 13:46
 */
public class ThreadState {
    public static void main(String[] args) {
        for (Thread.State  state: Thread.State.values()){
            System.out.println(state);
        }
    }
}

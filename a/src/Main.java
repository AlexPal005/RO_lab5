import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        new Main().start();
    }
    private void start(){
        CyclicBarrier barrier = new CyclicBarrier(2);
        String[] military_arr = new String[100];
        Military military = new Military(military_arr);
        military.random();
        military.show();
        System.out.println();

        Executor executor = new Executor();
        ExecutorThread executor_thread = new ExecutorThread(executor, 1, barrier);
        ExecutorThread executor_thread2 = new ExecutorThread(executor, 2, barrier);
        new Thread(executor_thread).start();
        new Thread(executor_thread2).start();
    }
}
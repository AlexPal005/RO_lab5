import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ExecutorThread implements Runnable{
    private Executor executor;
    private int range;
    private CyclicBarrier barrier;
    public ExecutorThread(Executor executor,int range,CyclicBarrier barrier){
        this.executor = executor;
        this.range = range;
        this.barrier = barrier;
    }
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(2000);
                executor.show_executor(range);
                executor.change_side(range);
                barrier.await();
                if(executor.is_same()){
                    break;
                }

            }catch(InterruptedException | BrokenBarrierException e ){
                System.out.println(e);
            }
        }
    }
}

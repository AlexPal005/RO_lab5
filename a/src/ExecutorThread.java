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
                barrier.await();
                executor.show_executor(range);
                if(executor.is_same()){
                    break;
                }
                executor.change_side(range);

            }catch(InterruptedException | BrokenBarrierException e ){
                System.out.println(e);
            }
        }
    }
}

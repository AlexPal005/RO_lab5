import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadChanger implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private Changer changer;
    private Comparator comparator;
    public ThreadChanger(CyclicBarrier cyclicBarrier, Changer changer, Comparator comparator){
        this.cyclicBarrier = cyclicBarrier;
        this.changer = changer;
        this.comparator = comparator;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                int count = changer.get_count_AB();
                comparator.add_count(count);
                System.out.println(count + " " + Thread.currentThread().getName());
                cyclicBarrier.await();
                if(comparator.compare()){
                    System.out.println("Stoped " + Thread.currentThread().getName());
                    break;
                }
                comparator.clear();
                changer.change_later();
                System.out.println();
            }catch(InterruptedException | BrokenBarrierException e){
                throw new RuntimeException(e);
            }
        }

    }
}

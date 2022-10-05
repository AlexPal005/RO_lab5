import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
        Comparator comparator = new Comparator();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        ////////////////////////////////////////////////////////////////////////////
        String str1 = "ABCDBDCA";
        Changer changer1 = new Changer(str1);
        ThreadChanger thread_changer1 = new ThreadChanger(cyclicBarrier,changer1, comparator);
        Thread t1 = new Thread(thread_changer1);
        t1.start();

        ////////////////////////////////////////////////////////////////////////////
        String str2 = "ACCDBDCA";
        Changer changer2 = new Changer(str2);
        ThreadChanger thread_changer2 = new ThreadChanger(cyclicBarrier,changer2,comparator);
        Thread t2 = new Thread(thread_changer2);
        t2.start();

        ////////////////////////////////////////////////////////////////////////////
        String str3 = "BBCAADCA";
        Changer changer3 = new Changer(str3);
        ThreadChanger thread_changer3 = new ThreadChanger(cyclicBarrier,changer3,comparator);
        Thread t3 = new Thread(thread_changer3);
        t3.start();

        ////////////////////////////////////////////////////////////////////////////
        String str4 = "ABBDBDCA";
        Changer changer4 = new Changer(str4);
        ThreadChanger thread_changer4= new ThreadChanger(cyclicBarrier,changer4,comparator);
        Thread t4 = new Thread(thread_changer4);
        t4.start();

    }
}
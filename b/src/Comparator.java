import java.util.ArrayList;

public class Comparator {
    private ArrayList<Integer> arr_counts = new ArrayList<>();

    public synchronized void  add_count(int count){
        this.arr_counts.add(count);
    }
    public synchronized boolean compare(){
        int temp = 1;
        for ( int i = 0; i < arr_counts.size() - 1; i++){
            if(arr_counts.get(i) == arr_counts.get(i + 1)){
                temp++;
            }
            else{
                temp = 1;
            }
        }
        if ( temp >= 3){
            return true;
        }
        return false;
    }
    public synchronized void clear(){
        for( int i = 0 ; i < arr_counts.size(); i ++) {
            arr_counts.remove(i);
        }
    }

}

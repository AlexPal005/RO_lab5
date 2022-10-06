public class Executor {

    private Military military;
    public Executor(Military military){
        this.military = military;
    }
    public synchronized void change_side(int range){
        int iterator;
        if(range == 1){
            iterator = 0;
        }
        else{
            iterator = range * 50 - 50;
        }
        for( int i = iterator ; i < iterator + 49; i++){
            if(!Military.military[i].equals(Military.military[i+1])){
                if(Military.military[i].equals("Вліво")){
                    Military.military[i] = "Вправо";
                }
                else{
                    Military.military[i] = "Вліво";
                }
            }
        }
    }

    public synchronized boolean is_same(){
        int count = 0;
        for(int i = 0; i < Military.military.length; i++){
            if(Military.military[0].equals(Military.military[i])){
                count++;
            }
        }
        if (count == Military.military.length){
            return true;
        }
        return false;
    }
    public synchronized void show_executor(int range){
        int iterator;
        if(range == 1){
            iterator = 0;
        }
        else{
            iterator = range * 50 - 50;
        }
        System.out.println(Thread.currentThread().getName());
        for(int i = iterator; i < iterator + 49; i++){
            System.out.print(Military.military[i] + " ");
        }
        System.out.println();

    }

}

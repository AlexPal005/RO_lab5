public class Military {
    public static String[] military;
    public Military(String[] military){
        this.military = military;
    }
    public void show(){
        for(int i = 0; i < military.length; i++){
            System.out.print(military[i] + " ");
        }
    }
    public void random(){
        for(int i = 0 ; i < military.length; i++) {
            int rand = (int) (Math.random() * 2);
            if (rand == 0) {
                military[i] = "Вліво";
            }
            else{
                military[i] = "Вправо";
            }
        }
    }

}

import java.util.concurrent.CyclicBarrier;

public class Changer {
   private String str;
   public Changer(String str){
       this.str = str;
   }
   public void change_later(){
       int rand = (int)( Math.random() * str.length());
       char[] symbols = str.toCharArray();

       if(symbols[rand] == 'A'){
           symbols[rand] = 'C';
       }
       else if(symbols[rand] == 'C'){
           symbols[rand] = 'A';
       }
       else if(symbols[rand] == 'B'){
           symbols[rand] = 'D';
       }
       else if(symbols[rand] == 'D'){
           symbols[rand] = 'B';
       }
       str = String.valueOf(symbols);
   }
   public int get_count_AB(){
       char[] symbols = str.toCharArray();
       int count = 0;
       for(int i = 0; i < symbols.length; i++){
           if(symbols[i] == 'A' || symbols[i] == 'B'){
               count++;
           }
       }
       return count;
   }
}

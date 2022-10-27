import java.util.Random;
import java.util.Scanner;

public class randomword {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String output= "error";
        int i=1;
        do{
            System.out.print("/");
            Random rnd = new Random();
            if(rnd.nextInt(i) == 0) output= sc.next();
            else sc.next();
            i++;
        }while(sc.hasNext());
        System.out.print(output);
    }
}

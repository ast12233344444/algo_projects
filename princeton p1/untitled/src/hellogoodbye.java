import java.util.Scanner;

public class hellogoodbye {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String name1 = sc.next();
        String name2 = sc.next();
        System.out.printf("Hello %s and %s \n", name1, name2);
        System.out.printf("goodbye %s and %s \n", name2,name1);
    }
}

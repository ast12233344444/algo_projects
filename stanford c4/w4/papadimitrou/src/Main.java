import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static boolean[] createrandom(int length){
        boolean[] random = new boolean[length];
        Random rand = new Random();

        for(int i=0;i<length;i++){
            random[i] = rand.nextBoolean();
        }

        return  random;
    }

    public static boolean check_statement(boolean[] values, two_statement statement){
        boolean first= false, second= false;
        if(statement.first_not)first = ! values[statement.firstind];
        else first = values[statement.firstind];

        if(statement.second_not)second = !values[statement.secind];
        else second = values[statement.secind];

        return first || second;
    }

    public static two_statement get_false(boolean[] values, two_statement[] statements){
        int len = statements.length;

        for(int i=0;i<len;i++){
            if(! check_statement(values, statements[i])) return statements[i];
        }
        return null;
    }

    public static boolean papadimitrous(two_statement[] statements){
        int len = statements.length;
        long innerlimit = 2* len*len;
        int loglen = (int)Math.ceil(Math.log(len));
        Random randgen = new Random();
        for(int i=0;i <loglen;i++){
            boolean[] randindices = createrandom(len+1);
            System.out.println("\t at inner loop" + i);
            for(long j=0;j<innerlimit;j++){
                if(j%10000 == 0)System.out.println("\t\tat j:"+j);
                two_statement falsest = get_false(randindices, statements);
                if(falsest == null)return true;
                else{
                    /*System.out.println("at line 48");*/
                    /*int state_to_change = randgen.nextInt(falses.size());
                    two_statement falsest = falses.get(state_to_change);*/
                    boolean ht = randgen.nextBoolean();
                    if(ht)randindices[falsest.firstind] = !randindices[falsest.firstind];
                    else randindices[falsest.secind] = !randindices[falsest.secind];
                }
            }
        }

        return false;
    }

    public static two_statement[] create_statements(String address){
        In in = new In(address);
        int elements = in.readInt();
        two_statement[] statements = new two_statement[elements];

        for(int i=0; i< elements;i++){
            String str1 = in.readString();
            String str2 = in.readString();
            boolean not1=false,not2=false;
            int ind1=-1, ind2=-1;

            if(str1.charAt(0) == '-'){
                not1 = true;
                ind1 = Integer.parseInt(str1.substring(1));
            }else{
                ind1 = Integer.parseInt(str1);
            }

            if(str2.charAt(0) == '-'){
                not2 = true;
                ind2 = Integer.parseInt(str2.substring(1));
            }else{
                ind2=Integer.parseInt(str2);
            }

            statements[i] = new two_statement(ind1, ind2, not1, not2);
        }

        return statements;
    }

    public static void main(String[] args) {
        two_statement[] statements = create_statements("test1.txt");
        /*System.out.println("Solving the first case:");
        System.out.println("first file's satisfiability is: " + papadimitrous(statements));

        System.out.println("Solving the second case:");
        statements = create_statements("sat2.txt");
        System.out.println("second file's satisfiability is: " + papadimitrous(statements));

        System.out.println("Solving the third case:");
        statements = create_statements("sat3.txt");
        System.out.println("third file's satisfiability is: " + papadimitrous(statements));

        System.out.println("Solving the fourth case:");
        statements = create_statements("sat4.txt");
        System.out.println("fourth file's satisfiability is: " + papadimitrous(statements));

        System.out.println("Solving the sixth case:");
        statements = create_statements("sat6.txt");
        System.out.println("sixth file's satisfiability is: " + papadimitrous(statements));

        System.out.println("Solving the fifth case:");
        statements = create_statements("sat5.txt");
        System.out.println("fifth file's satisfiability is: " + papadimitrous(statements));

         */
        System.out.println("first test case result is " + papadimitrous(statements));
        statements = create_statements("test2.txt");
        System.out.println("second test case result is " + papadimitrous(statements));
        statements = create_statements("test3.txt");
        System.out.println("third test case result is " + papadimitrous(statements));
        statements = create_statements("test4.txt");
        System.out.println("fourth test case result is " + papadimitrous(statements));
        /*statements = create_statements("test5.txt");
        System.out.println("fifth test case result is " + papadimitrous(statements));*/
        statements = create_statements("test6.txt");
        System.out.println("sixtth test case result is " + papadimitrous(statements));


        System.out.println("Hello world!");
    }
}
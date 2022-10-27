import java.io.*;

public class Move_to_front {
    private static int R = 256;
    public static void encode(In in, BinaryOut out){
        LinkedList extascii = new LinkedList();
        for(int i =0;i<R;i++) {
            extascii.add((char)i);
        }
        System.out.println("list construction finished" );
        while(!in.isEmpty()){
            char c = in.readChar();

            int a = extascii.MoveToFirst(c);

            out.write(a, 8);
            System.out.println(a);
        }
        out.flush();
        out.close();

        /*extascii.listcheck();*/
    }

    public static void decode(BinaryIn in, BinaryOut out){
        LinkedList extascii = new LinkedList();
        for(int i =0;i<R;i++) {
            extascii.add((char)i);
        }
        /*System.out.println("list construction finished" );*/
        while(!in.isEmpty()){
            int c = in.readInt(8);
            char a = extascii.get_and_move(c);
            out.write(a, 8);
            System.out.println(a);
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args){
        In in = new In("abra.txt");
        BinaryOut out = new BinaryOut("encoded.txt");
        encode(in, out);

        BinaryIn in2 = new BinaryIn("encoded.txt");
        BinaryOut out2 = new BinaryOut("eşek.txt");
        decode(in2, out2);
    }
}

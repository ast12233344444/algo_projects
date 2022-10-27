import java.util.ArrayList;

public class BoggleBoard {
    public class square{
        char value;
        ArrayList<Integer> neighbors;
        public square(char val){
            value = val;
            neighbors = new ArrayList<Integer>();
        }
    }
    int m;
    int n;
    square[] board;
    public BoggleBoard(String directory){
        In in = new In(directory);
        m = in.readInt();
        n = in.readInt();
        board = new square[m*n];
        for(int i = 0 ; i< m; i++){
            for(int j =0;j<n;j++){
                placeTile(i, j, in.readString().charAt(0));
            }
        }
    }

    public int rows(){
        return m;
    }

    public int cols(){
        return n;
    }

    public char getLetter(int i, int j){
        return board[i*n + j].value;
    }

    public void placeTile(int i, int j, char val){
        square s = new square(val);
        if(j > 0){
            s.neighbors.add(i*n + j -1);
            if(i > 0){
                s.neighbors.add(i*n + j - n - 1);

            }
            if(i < m - 1){
                s.neighbors.add(i*n + j + n - 1);

            }
        }
        if(j < n-1){
            s.neighbors.add(i*n + j +1);
            if(i > 0){s.neighbors.add(i*n + j - n + 1);}
            if(i < m - 1){s.neighbors.add(i*n + j + n + 1);}
        }

        if(i> 0){s.neighbors.add(i*n + j - n);}
        if(i < m-1){s.neighbors.add(i*n + j + n);}
        board[i*n + j] = s;

    }

    public String toString(){
        String result = "";
        for(int i=0;i< m;i++){
            for(int j =0;j<n;j++){
                result += board[i*n + j].value;
                result += " ";
            }
            result += "\n";
        }
        return  result;
    }

    public static void main(String[] args){
        BoggleBoard b = new BoggleBoard("board-q.txt");
        System.out.println(b);
        System.out.println("-------------------------------------------------");
        for(int i = 0; i< b.m * b.n ; i++){
            square s  = b.board[i];
            System.out.println(s.value + " : ");
            for(Integer I: s.neighbors){
                System.out.println("-> " + b.board[I].value);
            }
        }
    }
}

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    int moves;
    List<Board> boards;
    public Solver(Board init){
        boards = new LinkedList<Board>();
        moves = 0;
        Search_Node node = new Search_Node(init, null);
        if(node.board.Manhattan() == 0)return;

        boards.add(node.board);
        priority_queue<Search_Node> pq = new priority_queue<Search_Node>();
        while(node.board.Manhattan() != 0){
            for(Board b: node.board.neighbors()){
                pq.insert(new Search_Node(b, node));
            }

            node = pq.getMin();
            boards.add(node.board);
            moves++;
        }
    }

    public int moves(){return moves;}

    public Iterable<Board> Moves(){return boards;}

    public static void  main(String[]args){
        int[][] tiles = {{1,2,3},{0,4,6},{7,5,8}};

        Board B = new Board(tiles);
        Solver s = new Solver(B);
        System.out.println(s.moves);
        for(Board b: s.boards){
            System.out.println(b);
        }
    }
}

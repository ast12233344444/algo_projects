import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {
    Trie dictionary;
    BoggleBoard board;
    boolean[] isused;
    CharStack stack;
    Set<String> validwords;

    public BoggleSolver(String directory){
        dictionary = new Trie(directory);
    }

    public Iterable<String> getAllValidWords(BoggleBoard board_){
        int len = board_.n * board_.m;
        board = board_;
        validwords = new HashSet<String>();
        stack = new CharStack();
        isused = new boolean[len];

        for(int i=0;i< len;i++){
            solve_w_dfs(i);
        }

        return validwords;
    }

    private void solve_w_dfs(int index){
        BoggleBoard.square n = board.board[index];
        char c= n.value;
        stack.put(c);

        boolean qstate = false;
        if(c == 'Q'){
            stack.put('U');
            qstate = true;
        }

        int var = dictionary.has(stack);
        if(var == -1){
            stack.pop();
            if(qstate)stack.pop();
            return;
        }

        isused[index] = true;

        if(var > 0){
            validwords.add(stack.toString());
        }

        for(Integer neighbor: n.neighbors){
            if(!isused[neighbor]) solve_w_dfs(neighbor);
        }

        stack.pop();
        isused[index] = false;
        if(qstate)stack.pop();
    }

    public int scoreOf(String word){
        int depth = word.length();
        int value = -1;
        if(depth < 3) value = 0;
        if(depth < 5) value = 1;
        else if(depth < 6) value = 2;
        else if(depth < 7) value = 3;
        else if(depth < 8) value = 5;
        else value = 11;
        return value;
    }

    public int Scoreresults(){
        int score = 0;
        for(String s:validwords){
            score += scoreOf(s);
        }
        return score;
    }

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        BoggleSolver solver = new BoggleSolver("dictionary-algs4.txt");
        long t2 = System.currentTimeMillis();

        BoggleBoard board = new BoggleBoard("board4x4.txt");
        long t3 = System.currentTimeMillis();
        solver.getAllValidWords(board);
        long t4 = System.currentTimeMillis();
        for (String word : solver.getAllValidWords(board)) {
            System.out.println(word);
        }
        System.out.println((t2 - t1) + " miliseconds preparing solver");
        System.out.println((t3 - t2) + " miliseconds preparing board");
        System.out.println((t3 - t2) + " miliseconds solving");

        System.out.println(solver.Scoreresults());

    }
}

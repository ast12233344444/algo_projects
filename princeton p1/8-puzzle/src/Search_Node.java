public class Search_Node implements Comparable<Search_Node>{
    public Board board;
    int prevmoves;
    Search_Node prev;

    public Search_Node(Board b, Search_Node prev_){
        board = b;
        prev = prev_;
        if(prev_ != null) prevmoves = prev.prevmoves + 1;
        else prevmoves = 0;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public int compareTo(Search_Node o) {
        return this.board.Manhattan() - o.getBoard().Manhattan();
    }
}

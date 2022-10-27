import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    public int[][] tiles;
    int dimension;

    public Board(int[][] tiles_){
        tiles = tiles_;
        dimension = tiles_.length;
    }

    @Override
    public String toString() {
        String a = "";
        for(int i = 0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                a += tiles[i][j];
            }
            a += "\n";
        }
        return a;
    }

    public int dimension(){
        return dimension;
    }

    public int Hamming(){
        int error = 0;
        for(int i= 0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(tiles[i][j] == 0){
                    continue;
                }

                int exp_val = i*dimension + j + 1;
                if(tiles[i][j] != exp_val) error++;
            }
        }

        return error;
    }

    public int Manhattan(){
        int error = 0;
        for(int i=0;i<dimension;i++){
            for(int j=0;j< dimension;j++){
                int act_val = tiles[i][j];
                if(act_val == 0)continue;

                int sup_row = (act_val-1) / dimension;
                int sup_col = (act_val-1) % dimension;
                int err = Math.abs(i - sup_row) + Math.abs(j - sup_col);
                error += err;
            }
        }

        return error;
    }

    public boolean isGoal(){
        for(int i =0; i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(i==dimension-1 && j==dimension-1){
                    if(tiles[i][j] == 0) continue;
                }
                int sup_val = i*dimension+j+1;
                if(tiles[i][j] != sup_val)return false;
            }
        }
        return true;
    }

    public boolean equals(Board y){
        if(y.dimension() != dimension) return false;
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(tiles[i][j] != y.tiles[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors(){
        int zero_row=0;
        int zero_col=0;
        ArrayList<Board> neighbors = new ArrayList<Board>();
        for(int i=0; i<dimension;i++){
            for(int j=0;j<dimension;j++){
                if(tiles[i][j]==0){
                    zero_row = i;
                    zero_col = j;
                }
            }
        }
        if(zero_row > 0){
            int[][] copy = new int[dimension][dimension];
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    copy[i][j] = tiles[i][j];
                }
            }

            int temp = copy[zero_row-1][zero_col];
            copy[zero_row-1][zero_col] = copy[zero_row][zero_col];
            copy[zero_row][zero_col] = temp;

            Board x = new Board(copy);
            neighbors.add(x);
        }

        if(zero_row < dimension - 1){
            int[][] copy = new int[dimension][dimension];
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    copy[i][j] = tiles[i][j];
                }
            }

            int temp = copy[zero_row+1][zero_col];
            copy[zero_row+1][zero_col] = 0;
            copy[zero_row][zero_col] = temp;

            neighbors.add(new Board(copy));
        }

        if(zero_col > 0){
            int[][] copy = new int[dimension][dimension];
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    copy[i][j] = tiles[i][j];
                }
            }

            int temp = copy[zero_row][zero_col-1];
            copy[zero_row][zero_col-1] = copy[zero_row][zero_col];
            copy[zero_row][zero_col] = temp;

            Board x = new Board(copy);
            neighbors.add(x);
        }

        if(zero_col < dimension-1){
            int[][] copy = new int[dimension][dimension];
            for(int i=0;i<dimension;i++){
                for(int j=0;j<dimension;j++){
                    copy[i][j] = tiles[i][j];
                }
            }

            int temp = copy[zero_row][zero_col+1];
            copy[zero_row][zero_col+1] = 0;
            copy[zero_row][zero_col] = temp;

            neighbors.add(new Board(copy));
        }

        return neighbors;
    }

    public static void main(String[] args){

    }
}

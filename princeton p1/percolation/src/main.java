import java.util.Random;

public class main {
    public static void main(String[] args){
        for(int n = 10;n< 500; n+=10) {
            int m = 100000;
            int sum = 0;
            int column;
            int row;
            Random random = new Random();
            percolation perloc = new percolation(n);
            for (int j = 0; j < m; j++) {
                while (!perloc.perelocates()) {
                    column = random.nextInt(n);
                    row = random.nextInt(n);
                    if(!perloc.isopen(row,column)) {
                        perloc.open(row, column);
                        sum++;
                    }
                }
            }

            System.out.println(n+" "+ sum+ " "+ sum/(n*n*1.0));
        }
    }
}

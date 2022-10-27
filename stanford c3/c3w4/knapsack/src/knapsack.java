public class knapsack {


    public static long knapsack(Item[] items, int weightlimit){
        long[][] results_array = new long[2][weightlimit + 1];

        for(int i=1; i <= items.length;i++){
            Item curritem = items[i-1];

            for(int j = 0; j < curritem.weight; j++){
                results_array[1][j] = results_array[0][j];
            }

            for(int j = curritem.weight;j <= weightlimit;j++ ){
                long add = results_array[0][j-curritem.weight] + curritem.value;
                long dont_add = results_array[0][j];
                results_array[1][j] = Math.max(add, dont_add);
            }

            for(int j = 0; j <= weightlimit;j++){
                results_array[0][j] = results_array[1][j];
            }
        }

        return results_array[1][weightlimit];
    }

    public static void main(String[] args){
        In in = new In("data_big.txt");
        int m = in.readInt();
        int n = in.readInt();

        Item[] items = new Item[n];
        for(int i =0; i< n;i++){
            int valtemp = in.readInt();
            int sizetemp = in.readInt();
            items[i] = new Item(valtemp, sizetemp);
        }

        System.out.println(knapsack(items, m));
    }
}

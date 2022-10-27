import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class big_clusterer {
    public static void main(String[] args){
        long time1 = System.currentTimeMillis();
        In in = new In("big_cluster.txt");
        int k = in.readInt();
        int w = in.readInt();
        in.readLine();

        union_find uf = new union_find(k);
        boolean[][] vals = new boolean[k][w];
        ArrayList<weighted_edge> edges = new ArrayList<weighted_edge>();
        String temp;
        for(int i=0;i < k;i++){
            temp = in.readLine().replaceAll(" ", "");
            for(int m=0;m < w;m++){
                if(temp.charAt(m) == '1'){
                    vals[i][m] = true;
                }else if(temp.charAt(m) == '0'){
                    vals[i][m] = false;
                }else{
                    System.out.println("wrong inputs at 24");
                }
            }
        }

        for(int i=0;i<k;i++){
            if(i%1000 == 0)System.out.println(i + ".th iteration");
            for(int j = i+1; j <k;j++){

                if(hammingdistance(vals[i], vals[j])){
                    uf.connect(i+1, j+1);
                }
            }
        }
        System.out.println(clustercount(uf,k));
        long time2 = System.currentTimeMillis();
        System.out.println((time2 - time1)/1000 + " seconds running time");
    }

    public static int clustercount(union_find uf, int elements){
        HashSet<Integer> roots = new HashSet<>();
        for(int i=1;i<=elements;i++){
            if(! roots.contains(uf.root(i))){
                roots.add(uf.root(i));
            }
        }
        return roots.size();
    }

    public static boolean hammingdistance(boolean[] a, boolean[] b){
        int len = a.length;
        int cost =0;
        if(b.length!= a.length) {
            System.out.println("length mismatch " + a.length + " " + b.length);
        }
        for(int i=0;i<len;i++){
            if(a[i] != b[i])cost++;
            if(cost > 2)return false;
        }
        return true;
    }
}

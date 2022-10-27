public class weighted_edge implements Comparable<weighted_edge>{
    int m;
    int n;
    int weight;

    public weighted_edge(int m_, int n_, int weight_){
        m = m_;
        n= n_;
        weight = weight_;
    }

    public int other(int k){
        if(k == m)return n;
        else if(k == n) return m;
        return -1;
    }

    @Override
    public int compareTo(weighted_edge we){
        if(this.weight >= we.weight)return +1;
        else if(we.weight > this.weight)return -1;
        return 0;
    }
}

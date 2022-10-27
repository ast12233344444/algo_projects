public class quick_union {
    int[] grid;

    public quick_union(int n){
        grid = new int[n];
        for(int i = 0; i< n;i++){
            grid[i] = i;
        }
    }

    public int root(int p){
        while(grid[p] != p)p = grid[p];
        return p;
    }

    public void union(int p, int q){
        int x = root(p);
        int y = root(q);
        grid[x] = y;
    }

    public boolean findunion(int p ,int q){
        return root(p) == root(q);
    }
}

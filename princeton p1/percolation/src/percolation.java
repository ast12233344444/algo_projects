

public class percolation {
    private boolean[] isopen;
    private quick_union quickunion;
    private int wid;
    int open;

    public percolation(int n){
        isopen = new boolean[n*n+2];
        isopen[0] = true;
        isopen[n*n+1] = true;
        quickunion = new quick_union(n*n+2);
        wid = n;
        open = 0;
    }

    public void open(int row, int column){
        int index = row * wid + column +1;
        open++ ;
        isopen[index] = true;
        if(index <= wid) quickunion.union(0,index);
        if(index >= (wid-1)*wid + 1) quickunion.union(index, wid*wid+1);
        if(isopen[index-1]) quickunion.union(index-1, index);
        if(isopen[index+1]) quickunion.union(index, index+1);
        if(index + wid <= wid*wid + 1 &&  isopen[index + wid]) quickunion.union(index, index+wid);
        if(index>= wid && isopen[index - wid]) quickunion.union(index - wid, index);
    }

    public int numberOfOpenSites(){
        return open;
    }

    public boolean perelocates(){
        return quickunion.findunion(0, wid * wid + 1);
    }

    public boolean isfull(int row, int column){
        return quickunion.findunion(0, row*wid + column +1);
    }

    public boolean isopen(int row , int column){
        return isopen[row * wid  + column +1];
    }
}

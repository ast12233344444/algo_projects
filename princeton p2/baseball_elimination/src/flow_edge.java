public class flow_edge {
    int v ;
    int w;
    int capacity;
    int flow;
    public flow_edge(int v_, int w_, int capacity_){
        v = v_;
        w = w_;
        capacity = capacity_;
        flow = 0;
    }

    public int from(){return v;}

    public int to(){return w;}

    public int other(int p){
        if(p==v)return w;
        if(p==w)return v;
        return -1;
    }

    public int usable_capacity_to(int p){
        if(p == w) return capacity - flow;
        else if(p==v) return flow;
        return -1;
    }

    public void change_flow(int direction ,int amount){
        if(direction == w) flow += amount;
        else if(direction == v) flow -= amount;
        if(flow > capacity || flow < 0)throw new IllegalArgumentException("!!!!yanlış flow değişimi!!!!");
    }

}

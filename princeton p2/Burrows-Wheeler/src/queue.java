import java.util.ArrayList;

public class queue<Type>{
    ArrayList<Type> queue;
    int n;
    public queue(){
        queue = new ArrayList<Type>();
        n = 0;
    }

    public void enque(Type val){
        queue.add(val);
    }

    public Type deque(){
        return queue.get(n++);
    }

    public boolean isempty(){
        return n == queue.size();
    }
}

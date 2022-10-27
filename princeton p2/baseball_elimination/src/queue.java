import java.util.ArrayList;

public class queue<Type>{
    ArrayList<Type> queue;
    int s;

    public queue(){
        queue = new ArrayList<Type>();
        s = 0;
    }

    public boolean isempty(){
        return s == queue.size();
    }

    public void enque(Type var){
        queue.add(var);
    }

    public Type deque(){
        return queue.get(s++);
    }
}

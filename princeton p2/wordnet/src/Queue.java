import java.util.ArrayList;


public class Queue<Type>{
    ArrayList<Type> al;
    int firstindex;
    public Queue(){
        al = new ArrayList<Type>();
        firstindex = 0;
    }

    public void enque(Type t){
        al.add(t);
    }

    public Type deque(){
        return al.get(firstindex++);
    }

    public Type first(){
        if(! isEmpty()){
        return al.get(firstindex);
        }
        return null;
    }

    public boolean isEmpty(){
        return firstindex == al.size();
    }
}

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class deque<Type> implements Iterable<Type>{
    private class Node{
        Type var;
        Node next;
        Node prev;
    }
    private Node first;
    private Node last;

    public deque()
    {
        first = null;
        last = null;
    }

    public void addFirst(Type item){
        if(item == null) throw new IllegalArgumentException("null giremezsin");
        Node oldfirst = first;
        first = new Node();
        first.var = item;
        first.next = oldfirst;
        first.prev = null;

        if(last == null) last = first;
    }

    public void addLast(Type item){
        if(item == null) throw new IllegalArgumentException("null giremezsin");
        Node newLast = new Node();
        newLast.var = item;
        newLast.next = null;
        newLast.prev = last;
        last.next = newLast;
        last = newLast;

        if(first == null) first = last;
    }

    public Type removeFirst(){
        if(first == null) throw new NoSuchElementException("daha eleman yok");
        Node oldfirst = first;
        first = first.next;
        first.prev = null;

        return oldfirst.var;
    }

    public Type removeLast(){
        if(last == null)throw new NoSuchElementException("daha eleman yok");
        Node oldLast = last;
        last = oldLast.prev;
        last.next=null;

        return oldLast.var;
    }


    public Iterator<Type> iterator(){
        return new dequeIterator();
    }

    private class dequeIterator implements Iterator<Type>{
        private Node current = first;

        @Override
        public boolean hasNext() {
            if(current == null) return false;
            return true;
        }

         @Override public Type next(){
            if(! hasNext()) throw new NoSuchElementException("verinin sonuna geldin");
            Type var = current.var;
            current = current.next;
            return var;
         }

         public void remove(){throw new UnsupportedOperationException("kaldırmak yasak");}
    }

    public static void main(String[] args){
        deque<Integer> dizi = new deque<>();
        dizi.addFirst(10);
        dizi.addLast(7);
        dizi.addFirst(5);
        dizi.addLast(8);
        dizi.addLast(17);
        System.out.println(dizi.removeFirst());
        dizi.addFirst(3);
        dizi.addLast(4);
        System.out.println(dizi.removeLast());
        dizi.addLast(15);
        System.out.println("----------------------------------------");
        for(Integer eleman: dizi){
            System.out.println(eleman);
        }


    }
}

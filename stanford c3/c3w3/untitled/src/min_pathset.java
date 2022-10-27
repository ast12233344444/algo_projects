import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class min_pathset {
    public static ArrayList<Long> computeset(ArrayList<Integer> orderedvertices){
        ArrayList<Long> nth_solutions = new ArrayList<Long>();

        nth_solutions.add((long) orderedvertices.get(0));
        if(orderedvertices.get(1) > orderedvertices.get(0)){
            nth_solutions.add((long)orderedvertices.get(1));
        }else{
            nth_solutions.add((long)0);
        }

        int len = orderedvertices.size();
        for(int i=2; i < len; i++){
            if(nth_solutions.get(i-1) >= nth_solutions.get(i-2) + orderedvertices.get(i)){
                nth_solutions.add(nth_solutions.get(i-1));
            }else{
                nth_solutions.add(nth_solutions.get(i-2) + orderedvertices.get(i));
            }
        }

        return nth_solutions;
    }

    public static ArrayList<Integer> get_path(ArrayList<Long> resultarray){
        int len = resultarray.size();
        ArrayList<Integer> path = new ArrayList<Integer>();

        int i = len-1 ;
        while(i > 1){
            if(resultarray.get(i-1) == resultarray.get(i)){
                path.set(path.size()-1, i-1+1);
                i --;
            }else{
                path.add(i-2+1);
                i -= 2;
            }
        }
        path.add(i+1);


        return path;
    }

    public static void main(String[] args){
        In in = new In("path.txt");
        int k =in.readInt();
        ArrayList<Integer> vertices = new ArrayList<Integer>();

        for(int i=0;i<k;i++){
            vertices.add(in.readInt());
        }

        ArrayList<Long> solutions = computeset(vertices);
        /*for(long i:solutions){
            System.out.println(i);
        }*/
        ArrayList<Integer> path = get_path(solutions);
        for(int i:path){
            System.out.println(i);
        }

        if(path.contains(1))System.out.println("1 true");
        if(path.contains(2))System.out.println("2 true");
        if(path.contains(3))System.out.println("3 true");
        if(path.contains(4))System.out.println("4 true");
        if(path.contains(17))System.out.println("17 true");
        if(path.contains(117))System.out.println("117 true");
        if(path.contains(517))System.out.println("517 true");
        if(path.contains(997))System.out.println("997 true");
    }
}

import java.util.ArrayList;
import java.util.HashMap;

public class SAP {
    digraph G;
    public SAP(digraph g){
        G = g;
    }

    public int length(int m, int n){
        HashMap<Integer, Integer> ancestors_m = G.bfs(m);
        HashMap<Integer, Integer> ancestors_n = G.bfs(n);

        int shortest = Integer.MAX_VALUE;
        int len = G.get_n();
        for(int i= 0;i<len;i++){
            if(ancestors_m.containsKey(i) && ancestors_n.containsKey(i)){
                int newlen = ancestors_n.get(i) + ancestors_m.get(i);
                if(newlen < shortest) shortest = newlen;
            }
        }
        if(shortest <= len)return shortest;
        return -1;
    }

    public int ancestor(int m, int n){
        HashMap<Integer, Integer> ancestors_m = G.bfs(m);
        HashMap<Integer, Integer> ancestors_n = G.bfs(n);

        int ancestor = -1;
        int shortest = Integer.MAX_VALUE;
        int len = G.get_n();
        for(int i= 0;i<len;i++){
            if(ancestors_m.containsKey(i) && ancestors_n.containsKey(i)){
                int newlen = ancestors_n.get(i) + ancestors_m.get(i);
                if(newlen < shortest) {
                    shortest = newlen;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    public int length(Iterable<Integer> m, Iterable<Integer> n){
        HashMap<Integer, Integer> ancestors_m = G.serial_bfs(m);
        HashMap<Integer, Integer> ancestors_n = G.serial_bfs(n);
        int shortest = Integer.MAX_VALUE;
        int len = G.get_n();
        for(int i= 0;i<len;i++){
            if(ancestors_m.containsKey(i) && ancestors_n.containsKey(i)){
                int newlen = ancestors_n.get(i) + ancestors_m.get(i);
                if(newlen < shortest) shortest = newlen;
            }
        }
        if(shortest <= len)return shortest;
        return (int) Math.round(2* Math.log(len));
    }

    public int ancestor(Iterable<Integer> m, Iterable<Integer> n){
        HashMap<Integer, Integer> ancestors_m = G.serial_bfs(m);
        HashMap<Integer, Integer> ancestors_n = G.serial_bfs(n);

        int ancestor = -1;
        int shortest = Integer.MAX_VALUE;
        int len = G.get_n();
        for(int i= 0;i<len;i++){
            if(ancestors_m.containsKey(i) && ancestors_n.containsKey(i)){
                int newlen = ancestors_n.get(i) + ancestors_m.get(i);
                if(newlen < shortest) {
                    shortest = newlen;
                    ancestor = i;
                }
            }
        }
        return ancestor;
    }

    public int length(String s1, String s2){
        ArrayList<Integer> synset1 = G.synset_to_word.get(s1);
        ArrayList<Integer> synset2 = G.synset_to_word.get(s2);

        return length(synset1, synset2);
    }

    public String[] sca(String s1, String s2){
        ArrayList<Integer> synset1 = G.synset_to_word.get(s1);
        ArrayList<Integer> synset2 = G.synset_to_word.get(s2);

        int ancestor = ancestor(synset1, synset2);

        return G.getSynset(ancestor);
    }
}

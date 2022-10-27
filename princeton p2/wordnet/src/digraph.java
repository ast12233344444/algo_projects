import java.util.*;

public class digraph {
    private class node{
        public ArrayList<Integer> directions;
        int id;
        String[] synset;
        public node(String synset_, int id_){
            id = id_;
            synset = synset_.split(" ");
            directions = new ArrayList<Integer>();
            ArrayList<Integer> temp;
            for(String s:synset){
                if(synset_to_word.containsKey(s)){
                    temp = synset_to_word.get(s);
                    temp.add(id);
                    synset_to_word.put(s, temp);
                }
                else {
                    temp = new ArrayList<Integer>();
                    temp.add(id);
                    synset_to_word.put(s, temp);
                }
            }
        }

        public node(String[] synset_, int id_){
            id = id_;
            synset = synset_;
            directions = new ArrayList<Integer>();
        }

        public Iterable<Integer> adj() {
            return directions;
        }
    }

    ArrayList<node> graph;
    HashMap<String, ArrayList<Integer>> synset_to_word;
    int n;
    ArrayList<node> reversed;
    public digraph(String synsets, String hypernyms){
        n = 0;
        graph = new ArrayList<node>();
        synset_to_word = new HashMap<String, ArrayList<Integer>>();
        In synset = new In(synsets);
        String[] elements;
        while(synset.hasNextLine()){
            String line = synset.readLine();
            elements = line.split(",");
            insertnode(Integer.parseInt(elements[0]), elements[1]);
        }
        In hypernym =new In(hypernyms);
        while(hypernym.hasNextLine()){
            String line = hypernym.readLine();
            elements = line.split(",");
            for(int i = 1;i<elements.length;i++){
                addEdge(Integer.parseInt(elements[0]), Integer.parseInt(elements[i]));
            }
        }
        reversed = null;
    }

    public void insertnode(int nodeid, String synset){
        graph.add(nodeid, new node(synset,nodeid));
        n = graph.size();
    }

    public void addEdge(int from, int to){
        graph.get(from).directions.add(to);
    }

    public Iterable<Integer> adj(int nodeid){
        return graph.get(nodeid).directions;
    }

    public HashMap<Integer, Integer> bfs(int rootid){
        HashMap<Integer, Integer> ancestors_distances = new HashMap<Integer,Integer>();
        Queue<node> llnodes = new Queue<node>() ;
        Queue<Integer> distances = new Queue<Integer>();
        boolean[] isused = new boolean[n];

        isused[rootid] = true;
        llnodes.enque(graph.get(rootid));
        ancestors_distances.put(rootid, 0);
        distances.enque(0);
        while(!llnodes.isEmpty()){
            node n = llnodes.deque();
            int dist = distances.deque();
            ancestors_distances.put(n.id, dist);
            for(Integer i:n.adj()){
                if(! isused[i]){
                    isused[i] = true;
                    llnodes.enque(graph.get(i));
                    distances.enque(dist + 1);
                }
            }
        }

        return ancestors_distances;
    }

    public HashMap<Integer, Integer> serial_bfs(Iterable<Integer> roots){
        HashMap<Integer, Integer> ancestors_disctances =new HashMap<Integer, Integer>();
        boolean[] isused = new boolean[n];
        Queue<node> llnodes = new Queue<node>();
        Queue<Integer> distances = new Queue<Integer>();

        int currentdist = 0;
        for(Integer i:roots){
            if(! isused[i]){
                isused[i] = true;
                llnodes.enque(graph.get(i));
                distances.enque(currentdist);
            }
        }
    
        while(! llnodes.isEmpty()){
            while(distances.first() != null && distances.first() == currentdist){
                node n = llnodes.deque();
                int dist = distances.deque();
                ancestors_disctances.put(n.id, dist);

                for(Integer i: n.adj()){

                    if(! isused[i]) {
                        isused[i] = true;
                        llnodes.enque(graph.get(i));
                        distances.enque(dist + 1);
                    }
                }

            }
            currentdist++;
        }

        return ancestors_disctances;

    }

    public int get_n(){return n;}

    public String[] getSynset(int n){
        return graph.get(n).synset;
    }

    public ArrayList<node> reverse(){
        reversed = new ArrayList<node>();
        for(node n: graph){
            reversed.add(new node(n.synset, n.id));
        }
        
        for(node n: graph){
            for(Integer i: n.directions){
                reversed.get(i).directions.add(n.id);
            }
        }
        return reversed;
    }

    public ArrayList<Integer> topological_sort(ArrayList<node> array){
        
        int size = array.size();
        ArrayList<Integer> sorted = new ArrayList<Integer>();
        boolean[] isused = new boolean[size];

        for(int i = 0; i< size; i++){
            if(! isused[i]) dfs(array, sorted, isused, i);
        }

        for(int i=0;i<size/2;i++){
            Integer temp = sorted.get(i);
            sorted.set(i, sorted.get(size-i-1));
            sorted.set(size-i-1, temp);
        }

        return sorted;
    }

    private void dfs(ArrayList<node> array, ArrayList<Integer> subsetarray, boolean[] isusedarr, int root){
        isusedarr[root] = true;
        node n = array.get(root);
        for(Integer i:n.directions){
            if(! isusedarr[i]){
                dfs(array,subsetarray, isusedarr, i);
            }
        }
        subsetarray.add(root);
    }

    public boolean checkcyclicpattern(){
        
        ArrayList<node> reversed= reverse();
        int size= reversed.size();
         
        ArrayList<Integer> tsorted = topological_sort(reversed);
        
        boolean[] isused = new boolean[size];

        for(int i=0;i<tsorted.size();i++){
            isused[tsorted.get(i)] = true;
            node n = graph.get(tsorted.get(i));
            for(Integer Int:n.directions){
                if(graph.get(Int).directions.size() == 0)continue;
                if(!isused[Int]) return true;
            } 
        }
        return false;
    }   
}





























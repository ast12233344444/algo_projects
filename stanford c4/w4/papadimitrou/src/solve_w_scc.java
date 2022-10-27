public class solve_w_scc {
    public static void insert_connections(Graph g, int element1, boolean not1, int element2, boolean not2){
        int len = g.Graph.length/2;
        if(not1 == false){
            if(not2 == false){
                g.connect(element1 + len, element2);
                g.connect(element2 + len, element1);
            }else{
                g.connect(element1 + len, element2 + len);
                g.connect(element2, element1);
            }
        }else{
            if(not2 == false){
                g.connect(element1, element2);
                g.connect(element2 + len, element1 + len);
            }else{
                g.connect(element1, element2 + len);
                g.connect(element2, element1 + len);
            }
        }
    }

    public static Graph create_from_directory(String directory){
        In in = new In(directory);
        int k = in.readInt();
        Graph g = new Graph(2*k);

        for(int i=0;i<k;i++){
            boolean not1= false, not2 = false;
            int first=0, second = 0;
            String str1 = in.readString();
            String str2 = in.readString();

            if(str1.charAt(0) == '-'){
                not1 = true;
                first = Integer.parseInt(str1.substring(1));
            }else{
                first = Integer.parseInt(str1);
            }

            if(str2.charAt(0) == '-'){
                not2 = true;
                second = Integer.parseInt(str2.substring(1));
            }else{
                second = Integer.parseInt(str2);
            }

            insert_connections(g, first, not1, second, not2);
        }

        return g;
    }

    public static boolean test_satisfiability(Graph g){
        int len = g.Graph.length/2;
        int[] scheme = g.get_scc_scheme();

        for(int i=0;i<len;i++){
            if(scheme[i] == scheme[i+len])return false;
        }
        return true;
    }

    public static void main(String[] args){
        String file = "test1.txt";
        Graph g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "test2.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "test3.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "test4.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "test5.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat1.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat2.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat3.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat4.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat5.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

        file = "sat6.txt";
        g = create_from_directory(file);
        System.out.println(file + "'s satisfiability is: " + test_satisfiability(g));

    }
}

public class Outcast {
    Wordnet w;

    public Outcast(Wordnet wordnet){
        w = wordnet;
    }

    public String outcast(String[] nouns){
        int totaldistance;
        int maxdistance = -1;
        String outcast = null;
        for(String s: nouns){
            totaldistance = 0;
            for(String second:nouns){
                totaldistance += w.distance(s,second);
            }
            /*System.out.println(s + ": " + totaldistance);*/
            if(totaldistance > maxdistance){
                outcast = s;
                maxdistance = totaldistance;
            }
        }
        return outcast;
    }

    public static void main(String[] args){
        Wordnet w = new Wordnet("sets\\synsets.txt","sets\\hypernyms.txt");
        Outcast oc = new Outcast(w);
        String[] sets = {"sets\\outcast17.txt","sets\\outcast20.txt","sets\\outcast29.txt"};
        for(String s: sets){
            In in = new In(s);
            String[] strings = in.readAllStrings();
            System.out.println(oc.outcast(strings));
        }
    }
}

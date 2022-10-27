import java.util.ArrayList;
import java.util.HashMap;
import java.util.SplittableRandom;

public class BaseballElimination {
    public class Team{
        String name;
        int id;
        int numwins;
        int numlosses;
        int remaininggames;
        int[] remaininggameswithteams;
        public Team(String nm, int numw,int numl,  int rmg, int[] rmgwt){
            name = nm;
            numwins = numw;
            numlosses = numl;
            remaininggames = rmg;
            remaininggameswithteams = rmgwt;
            id = n++;
        }
    }
    int n;
    HashMap<String, Team> teams;
    ArrayList<String> teamnames;
    network netw;
    public BaseballElimination(String filename){
        In in = new In(filename);
        n =0;

        int teamnum =  in.readInt();
        teamnames = new ArrayList<String>();
        teams = new HashMap<String, Team>();

        for(int i=0;i<teamnum;i++){
            String name = in.readString();
            int wins = in.readInt();
            int losses = in.readInt();
            int remaining = in.readInt();
            int[] gameswteams = new int[teamnum];
            for(int j =0;j < teamnum;j++){
                gameswteams[j] = in.readInt();
            }

            teams.put(name, new Team(name, wins, losses, remaining, gameswteams));
            teamnames.add(name);
        }
    }

    public Iterable<String> teams(){
        return  teamnames;
    }

    public int wins(String team){
        return teams.get(team).numwins;
    }

    public int losses(String team){
        return teams.get(team).numlosses;
    }

    public int remaining(String team){
        return teams.get(team).remaininggames;
    }

    public int against(String t1, String t2){
        int t2ind = teams.get(t2).id;
        return teams.get(t1).remaininggameswithteams[t2ind];
    }

    public boolean iseliminated(String team){
        network net = makeagainstgraph(team);
        netw = net;
        return net.isEliminated();
    }

    public Iterable<String> certificateOfElimination(String team){
        return netw.who_eliminated();
    }

    public network makeagainstgraph(String team){
        network net = new network();

        int k = 0;
        for(int i=0;i<n;i++){
            if(teamnames.get(i).equals(team))continue;
            for(int j =i+1;j<n;j++){
                if(teamnames.get(j).equals(team))continue;

                k++;
                net.insertnode(i +" vs " + j);
                net.connect(0, k, teams.get(teamnames.get(i)).remaininggameswithteams[j]);
            }
        }
        int[] teampointlimits = new int[n-1];
        k = -1;

        for(int i=0;i<n;i++){
            if(teamnames.get(i).equals(team)){continue;}
            k++;
            teampointlimits[k] = teams.get(team).remaininggames + teams.get(team).numwins - teams.get(teamnames.get(i)).numwins;
            net.insertnode(teamnames.get(i));
        }

        net.fixnodecount(n-1, teampointlimits);

        k =0;
        int combinations = (n-1)*(n-2)/2;
        for(int i=0;i<n;i++){
            if(teamnames.get(i).equals(team))continue;
            for(int j =i+1;j<n;j++){
                if(teamnames.get(j).equals(team))continue;
                k++;

                net.connect(k, combinations + i , teams.get(teamnames.get(i)).remaininggameswithteams[j]);
                net.connect(k, combinations + j , teams.get(teamnames.get(i)).remaininggameswithteams[j]);

            }
        }

        return net;
    }

    public static void main(String[] args) {
         BaseballElimination division = new BaseballElimination("teams4.txt");
         for (String team : division.teams()) {
             if (division.iseliminated(team)) {
                 System.out.print(team + " is eliminated ");
                 System.out.print(team + " is eliminated by the subset R = { ");
                 for (String t : division.certificateOfElimination(team)) {
                     System.out.print(t + " ");
                 }
                 System.out.println("}");
             }
             else {
                 System.out.println(team + " is not eliminated");
             }
         }
    }
}

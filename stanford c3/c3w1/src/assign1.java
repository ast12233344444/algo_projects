import java.util.Arrays;

public class assign1 {
    private class job implements Comparable<job>{
        int prior;
        int len;
        double ratio;
        public job(int prior_, int len_){
            prior = prior_;
            len = len_;
            ratio = (prior * 1.0 )/(len * 1.0);
        }

        @Override
        public int compareTo(job o) {
            if(this.ratio > o.ratio){
                return -1;
            }
            else if(this.ratio < o.ratio){

                return +1;
            }else if(this.prior > o.prior){
                return -1;
            }
            else{
                return +1;
            }
        }
    }

    public job makejob(int prior, int len){
        return new job(prior, len);
    }

    public static void main(String[] args){
        In in = new In("jobs.txt");
        int k = in.readInt();
        job[] jobs = new job[k];
        assign1 assign = new assign1();

        for(int i = 0;i< k;i++){
            int prior = in.readInt();
            int lenghth = in.readInt();
            jobs[i] = assign.makejob(prior, lenghth);
        }

        Arrays.sort(jobs);

        System.out.println(jobs[0].ratio + " " + jobs[0].prior + " " + jobs[0].len);
        System.out.println(jobs[1].ratio+ " " + jobs[1].prior + " " + jobs[1].len);
        System.out.println(jobs[jobs.length-1].ratio + " " + jobs[jobs.length-1].prior + " " + jobs[jobs.length-1].len);


        int priorsum =0;
        long totaltime =0;
        for(int i=0;i<k;i++){
            totaltime += (priorsum + jobs[i].len) * jobs[i].prior;
            System.out.println(totaltime +"="+(priorsum + jobs[i].len)+" * "+(jobs[i].prior));
            priorsum += jobs[i].len;
        }

        System.out.println(totaltime);
    }
}

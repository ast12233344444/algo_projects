import java.lang.reflect.Array;
import java.util.*;

public class FastCollinearPoints {
    private LineSegment[] segments;
    int n_segments;

    private class point_slope implements Comparable<point_slope>{
        public Point p;
        public double slope;

        public point_slope(Point p_, double slope_){
            p = p_;
            slope = slope_;
        }

        @Override
        public int compareTo(point_slope o) {
            if(this.slope > o.slope) return 1;
            else if(this.slope < o.slope) return -1;
            return 0;
        }
    }

    public FastCollinearPoints(Point[] points){
        segments = new LineSegment[points.length];
        n_segments= 0;
        for(int i = 0;i < points.length;i++){
            Point pc = points[i];
            point_slope[] serie = new point_slope[points.length - (1 + i)];

            for(int j = i+1;j< points.length;j++){
                serie[j - (i+1)] = new point_slope(points[j], pc.slopeWith(points[j]));
            }

            Arrays.sort(serie);

            for(int j = 0;j < serie.length; j++){
                ArrayList<Point> arl = new ArrayList<Point>();
                arl.add(serie[j].p);
                int k=1;
                while(j+k < serie.length && serie[j].slope == serie[j+k].slope){
                    arl.add(serie[j+k].p);
                    k++;
                }
                if(k >= 3){
                    arl.add(pc);
                    Collections.sort(arl);
                    if(dedectifexist(arl.get(0), arl.get(arl.size()-1)) == false){
                        segments[n_segments++] = new LineSegment(arl.get(0), arl.get(arl.size()-1));
                    }

                    j += k-1;
                }
            }
        }
    }

    private boolean dedectifexist(Point p1, Point p2){
        for(LineSegment ls:segments){
            if(ls == null)break;
            if(ls.getp2().getX() == p2.getX() && ls.getp2().getY() == p2.getY() ){
                if(p1.slopeWith(p2) == ls.getp1().slopeWith(p2)){
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfSegments(){
        return n_segments;
    }

    public LineSegment[] segments(){
        return segments;
    }

    public static void main(String[] args){
        Point[] points = new Point[11];
        points[0] = new Point(3,3);
        points[1] = new Point(3,5);
        points[2] = new Point(5,3);
        points[3] = new Point(5,5);
        points[4] = new Point(5,7);
        points[5] = new Point(5,9);
        points[6] = new Point(7,7);
        points[7] = new Point(9,9);
        points[8] = new Point(11,11);
        points[9] = new Point(5,10);
        points[10] = new Point(5,11);




        FastCollinearPoints cp = new FastCollinearPoints(points);
        System.out.println(cp.numberOfSegments());
        for(LineSegment ls: cp.segments){
            System.out.println(ls);
        }
    }
}

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    public Point(int x_ , int y_){
        x = x_;
        y = y_;
    }

    public int compareTo(Point point){
        if(this.y > point.y) return 1;
        if(this.y < point.y) return -1;
        else return 0;
    }

    public int getX(){return x;}
    public int getY(){
        return y;
    }

    public double slopeWith(Point point){
        if(point.x - this.x==0) return Double.MAX_VALUE;
        return 1.0 *  (point.y-this.y) / (point.x - this.x);
    }

    public Comparator<Point> slopeOrder(){
        return new pointSlopeComparator(this);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")" ;
    }

    private class pointSlopeComparator implements Comparator<Point>{

        private Point point;

        public pointSlopeComparator(Point p){
            point = p;
        }

        @Override
        public int compare(Point p1, Point p2) {
            double s1 = point.slopeWith(p1);
            double s2 = point.slopeWith(p2);

            if(s1 > s2) return 1;
            if(s1 < s2) return -1;
            return 0;
        }
    }
}

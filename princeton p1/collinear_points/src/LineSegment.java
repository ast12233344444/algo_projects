public class LineSegment {
    private Point p1;
    private Point p2;
    private double slope;


    public LineSegment(Point p1_, Point p2_){
        p1 = p1_;
        p2 = p2_;
        slope = p1.slopeWith(p2);
    }

    public Point getp1(){
        return p1;
    }

    public Point getp2(){
        return p2;
    }

    @Override
    public String toString() {
        return p1 + "->" + p2;
    }
}

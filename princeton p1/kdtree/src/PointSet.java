import java.util.ArrayList;

public class PointSet {
    ArrayList<Point2D> points;

    public PointSet(){
        points = new ArrayList<Point2D>();
    }

    public boolean isEmpty(){
        return points.size() == 0;
    }

    public int size(){
        return points.size();
    }

    public void insert(Point2D p){
        points.add(p);
    }

    public boolean contains(Point2D p_){
        for(Point2D p: points){
            if(p.x() == p_.x() && p.y() == p_.y()) return true;
        }
        return false;
    }

    public void draw(){}

    public Iterable<Point2D> range(RectHV rect){
        ArrayList<Point2D> inpoints = new ArrayList<Point2D>();
        for(Point2D p: points){
            if(p.x() > rect.xmin() && p.x() < rect.xmax()){
                if(p.y() > rect.xmin() && p.y() < rect.xmax()){
                    inpoints.add(p);
                }
            }
        }
        return inpoints;
    }

    public Point2D nearest(Point2D p_){
        Double mindist = Double.MAX_VALUE;
        Point2D closep = null;
        for(Point2D p: points){
            Double dist = (p.x() - p_.x())*(p.x() - p_.x()) +  (p.y() - p_.y()) *(p.y() - p_.y());
            if(dist < mindist){
                mindist = dist;
                closep = p;
            }
        }
        return closep;
    }

}

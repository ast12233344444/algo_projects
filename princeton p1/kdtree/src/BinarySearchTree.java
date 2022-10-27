import org.w3c.dom.ranges.Range;

import java.util.ArrayList;

public class BinarySearchTree{

    private class Node{
        Point2D point;
        int seperation;

        Node left;
        Node right;
        public Node(Point2D point_,int seperation_){
            point = point_;
            seperation = seperation_;
            left = null;
            right = null;
        }
    }
    private Node root;
    private static final int HORIZONTAL = 0;
    private static final int VERTİCAL = 1;


    public BinarySearchTree(){
        root = null;
    }

    public void insert(Point2D p){
        root = insert(root, p, HORIZONTAL);
    }

    public Node insert(Node root, Point2D p, int parentseperation){
        if(root == null) return new Node(p, (1- parentseperation));

        if(root.seperation == VERTİCAL){
            if(p.x() > root.point.x()){
                root.right = insert(root.right, p, root.seperation);
            }
            else if(p.x() <= root.point.x()){
                root.left = insert(root.left, p, root.seperation);
            }
        }
        else if(root.seperation == HORIZONTAL){
            if(p.y() > root.point.y()){
                root.right = insert(root.right, p, root.seperation);
            }
            if(p.y() <= root.point.y()){
                root.left = insert(root.left, p, root.seperation);
            }
        }

        return root;
    }

    //TODO: RANGECHECKER
    //TODO: CLOSESTPOİNT
    public int RangeChecker(RectHV rect){
        return RangeChecker(rect, root);
    }

    private int RangeChecker(RectHV rect, Node root){
        int insidepoints= 0;
        if(root == null) return  insidepoints;

        if(rect.xmin() < root.point.x() && root.point.x() < rect.xmax()){
            if(rect.ymin() < root.point.y() && root.point.y() < rect.ymax()){
                insidepoints++;
            }
        }

        if(root.seperation == VERTİCAL){
            if(rect.xmax() <= root.point.x()){
                insidepoints += RangeChecker(rect, root.left);
            }
            else if(rect.xmin() <= root.point.x() && rect.xmax() > root.point.x()){
                insidepoints += RangeChecker(rect, root.left);
                insidepoints += RangeChecker(rect, root.right);
            }
            else if(rect.xmin() > root.point.x()){
                insidepoints += RangeChecker(rect, root.right);
            }
        }
        else if(root.seperation == HORIZONTAL){
            if(rect.ymax() <= root.point.y()){
                insidepoints += RangeChecker(rect, root.left);
            }
            else if(rect.ymin() <= root.point.y() && rect.ymax() > root.point.y()){
                insidepoints += RangeChecker(rect, root.left);
                insidepoints += RangeChecker(rect, root.right);
            }
            else if(rect.ymin() > root.point.y()){
                insidepoints += RangeChecker(rect, root.right);
            }
        }

        return insidepoints;
    }

    public Point2D NearestNeighbor(Point2D point){
        return NearestNeighbor(root, point, null);
    }

    public Point2D NearestNeighbor(Node root, Point2D point, Node closest){
        Point2D result = null;
        if(closest == null) closest = root;
        if(root == null) return closest.point;
        if(dist(closest.point, point) > dist(root.point, point)) closest = root;

        if(root.seperation == VERTİCAL){
            if(point.x() > root.point.x()){
                result = NearestNeighbor(root.right, point, closest);
                Point2D temp = NearestNeighbor(root.left, point, closest);
                if(result == null) result = temp;
                if(dist(result, point) > dist(temp, point)) result = temp;
            }
            if(point.x() <= root.point.x()){
                result = NearestNeighbor(root.left, point, closest);
                Point2D temp = NearestNeighbor(root.right, point, closest);
                if(dist(result, point) > dist(temp, point)) result = temp;
            }
        }
        else if(root.seperation == HORIZONTAL){
            if(point.y() > root.point.y()){
                result = NearestNeighbor(root.right, point, closest);
                Point2D temp = NearestNeighbor(root.left, point, closest);
                if(dist(result, point) > dist(temp, point)) result = temp;
            }
            if(point.y() <= root.point.y()){
                result = NearestNeighbor(root.left, point, closest);
                Point2D temp = NearestNeighbor(root.right, point, closest);
                if(dist(result, point) > dist(temp, point)) result = temp;
            }
        }

        return result;
    }

    private Double dist(Point2D p1, Point2D p2){
        return (p1.x() - p2.x()) * (p1.x() - p2.x()) + (p1.y() - p2.y()) * (p1.y() - p2.y());
    }

    public static void main(String[] args){
        BinarySearchTree arr = new BinarySearchTree();
        arr.insert(new Point2D(1,2));
        arr.insert(new Point2D(3,5));
        arr.insert(new Point2D(-1, 2));
        arr.insert(new Point2D(4,2));
        Point2D p = arr.NearestNeighbor(new Point2D(3,3));
        System.out.println(arr.RangeChecker(new RectHV(-2, 1,5,3)));

    }
}

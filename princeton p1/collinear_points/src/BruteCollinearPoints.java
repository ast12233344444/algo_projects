public class BruteCollinearPoints {
    private Point[] points;
    public LineSegment[] segments;
    int n_segment;


    public BruteCollinearPoints(Point[] points_) {
        points = points_;
        n_segment = 0;
        segments = new LineSegment[points_.length];
        int n_points = points.length;
        for (int i1 = 0; i1 < n_points; i1++) {
            for (int i2 = i1 + 1; i2 < n_points; i2++) {
                for (int i3 = i2 + 1; i3 < n_points; i3++) {
                    for (int i4 = i3 + 1; i4 < n_points; i4++) {
                        double s1 = points[i1].slopeWith(points[i2]);
                        double s2 = points[i2].slopeWith(points[i3]);
                        double s3 = points[i3].slopeWith(points[i4]);
                        if (s1 == s2 && s2 == s3) {
                            int y1 = points[i1].getY(), y2 = points[i2].getY(), y3 = points[i3].getY(), y4 = points[i4].getY();
                            int a = Math.max(Math.max(y1, y2), Math.max(y3, y4));
                            int b = Math.min(Math.min(y1, y2), Math.min(y3, y4));

                            Point maxp = new Point(0, 0);
                            Point minp = new Point(0, 0);
                            if (a == y1) maxp = points[i1];
                            else if (a == y2) maxp = points[i2];
                            else if (a == y3) maxp = points[i3];
                            else if (a == y4) maxp = points[i4];

                            if (b == y1) minp = points[i1];
                            else if (b == y2) minp = points[i2];
                            else if (b == y3) minp = points[i3];
                            else if (b == y4) minp = points[i4];

                            segments[n_segment++] = new LineSegment(minp, maxp);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return n_segment;
    }

    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args){
        Point[] points = new Point[8];
        points[0] = new Point(3,3);
        points[1] = new Point(3,5);
        points[2] = new Point(5,3);
        points[3] = new Point(5,5);
        points[4] = new Point(5,7);
        points[5] = new Point(5,9);
        points[6] = new Point(7,7);
        points[7] = new Point(9,9);

        BruteCollinearPoints cp = new BruteCollinearPoints(points);
        System.out.println(cp.numberOfSegments());
        for(LineSegment ls: cp.segments){
            System.out.println(ls);
        }
    }
}

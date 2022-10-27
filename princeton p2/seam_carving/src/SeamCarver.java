import java.awt.*;
import java.security.PublicKey;

public class SeamCarver {
    Picture picture;
    int pictureHeight;
    int picturewidth;

    public SeamCarver(Picture p){
        picture = p;
        picturewidth = p.width();
        pictureHeight = p.height();
    }

    public Picture picture(){ return picture;}

    public int height(){ return pictureHeight;}

    public int width(){ return picturewidth;}

    public double energy(int x, int y){
        if(x == 0 || x == picturewidth -1) return 1000;
        if(y == 0 || y == pictureHeight - 1) return 1000;

        Color cx1 = picture.get(x+1,y), cx2 = picture.get(x-1,y);
        Color cy1 = picture.get(x,y+1), cy2 = picture.get(x, y-1);
        double grad1 = Math.pow(cx1.getRed() - cx2.getRed(), 2);
        grad1 += Math.pow(cx1.getBlue()- cx2.getBlue(), 2);
        grad1 += Math.pow(cx1.getGreen()- cx2.getGreen(), 2);
        double grad2 = Math.pow(cy1.getRed() - cy2.getRed(), 2);
        grad2 += Math.pow(cy1.getBlue()- cy2.getBlue(), 2);
        grad2 += Math.pow(cy1.getGreen()- cy2.getGreen(), 2);
        return Math.sqrt(grad1 + grad2);
    }

    public Shortest_Path Create_graph(){
        Shortest_Path sp = new Shortest_Path();

        sp.insert(-1,-1, 0);

        for(int i=0;i<picturewidth;i++){
            for(int j =0;j<pictureHeight;j++){
                sp.insert(i, j, energy(i,j));
            }
        }

        sp.insert(picturewidth, pictureHeight, 0);
        return sp;
    }

    public int[] findHorizontalSeam(){
        int[] HorizontalSeam = new int[picturewidth];

        Shortest_Path sp = Create_graph();

        for(int i = 1;i <= pictureHeight; i++){
            sp.connect(0, i);
        }

        for(int i = 0; i < picturewidth -1 ; i++){
            for(int j=0; j < pictureHeight; j++){
                int currind = i*pictureHeight + j + 1;
                if(j != 0) sp.connect(currind, currind + pictureHeight - 1);
                if(j != pictureHeight -1) sp.connect(currind, currind + pictureHeight + 1);
                sp.connect(currind, currind+ pictureHeight);
            }
        }

        for(int i =0; i<pictureHeight;i++){
            int currind = pictureHeight * (picturewidth- 1) + i + 1;
            sp.connect(currind, pictureHeight*picturewidth +1 );
        }

        Shortest_Path.Node n = sp.shortestpath();

        n = n.prevnode;

        for(int i = picturewidth -1; i >=0; i--){
            HorizontalSeam[i] = n.y;
            n = n.prevnode;
        }

        return HorizontalSeam;
    }

    public int[] findVerticalSeam(){
        int[] VerticalSeam = new int[pictureHeight];

        Shortest_Path sp = Create_graph();

        for(int i = 0;i < picturewidth; i++){
            sp.connect(0, i * pictureHeight + 1);
        }

        for(int i = 0; i < pictureHeight - 1 ; i++){
            for(int j=0; j < picturewidth; j++){
                int currind = j*pictureHeight + i + 1;
                if(j != 0) sp.connect(currind, currind - pictureHeight + 1);
                if(j != picturewidth -1) sp.connect(currind, currind + pictureHeight + 1);
                sp.connect(currind, currind+ 1);
            }
        }

        for(int i =0; i<picturewidth;i++){
            int currind = pictureHeight *(i+1);
            sp.connect(currind, pictureHeight*picturewidth +1 );
        }

        Shortest_Path.Node n = sp.shortestpath();

        n = n.prevnode;

        for(int i = pictureHeight -1; i >=0; i--){
            VerticalSeam[i] = n.x;
            n = n.prevnode;
        }

        return VerticalSeam;
    }

    public void removeVerticalSeam(int[] gonepixels){
        Picture newp = new Picture(picturewidth-1, pictureHeight);

        for(int i = 0; i< pictureHeight;i++){
            int k = -1;
            for(int j=0;j<picturewidth -1;j++){
                if(j != gonepixels[i]) newp.set(++k, i, picture.get(j,i));
            }
        }

        picture = newp;
        picturewidth--;
    }


    public void removeHorizontalSeam(int[] gonepixels){
        Picture newp = new Picture(picturewidth, pictureHeight-1);

        for(int i = 0; i< picturewidth;i++){
            int k = -1;
            for(int j=0;j<pictureHeight-1;j++){
                if(j != gonepixels[i]) newp.set(i, ++k, picture.get(i,j));
            }
        }

        picture = newp;
        pictureHeight--;
    }


    public static void main(String[] args){
         SeamCarver sc = new SeamCarver(new Picture("seam\\images.jpeg"));
         int[] array = sc.findHorizontalSeam();
         for(int i:array){
             System.out.print( i + ", ");
         }
         System.out.println();

         array = sc.findVerticalSeam();
         for(int i:array){
            System.out.print( i + ", ");
         }
         System.out.println();
         long stime = System.currentTimeMillis();
         sc.picture.show();
         for(int i=0;i<200;i++){
             sc.removeHorizontalSeam(sc.findHorizontalSeam());
         }
         long ftime = System.currentTimeMillis();
         System.out.println(ftime - stime + "milisecondes passed");
         sc.picture.show();
    }


}

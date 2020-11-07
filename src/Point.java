import java.util.*;

public class Point implements Comparable<Point> {
   public Double X;
   public Double Y;
    
public Point() {
    X = 0.0;
    Y = 0.0;
}


public Point(Double X, Double Y) {
    this.X = X;
    this.Y = Y;
}



public Double getX() {
   return X;
}
public Double getY() {
    return Y;
}
public void setX(Double X) {
    this.X = X;
}
public void setY(Double Y) {
    this.Y = Y;
}

@Override
public String toString() {
    return "(" + X + ", " + Y + ")";
}

@Override
public int compareTo(Point o) {
    // TODO Auto-generated method stub
    if(this.X > o.X) {
        return 1;
    }else if(this.X < o.X) {
        return -1;
    }
    return 0;
}


}

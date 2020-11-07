import java.util.*;


public class quickHull {

    public static ArrayList<Point> Convex_hull = new ArrayList<>();


//Determines the distance of Point P from the line AB
public static Double distanceOfPointFromLine(Point linePointA, Point linePointB, Point P) {
    Double slope = (linePointB.Y - linePointA.Y) / (linePointB.X - linePointA.X);
    Double intercept = linePointA.Y - ((slope) * linePointA.X);
    return ((-slope * P.X) + (P.Y - intercept)) / Math.sqrt((slope * slope) + 1);
}

public static boolean insideTriangle(Point A, Point B, Point C, Point P) {
    Double equation1 = (P.X - A.X)*(B.Y - A.Y) - (P.Y - A.Y)*(B.X - A.X);
    Double equation2 = (P.X - B.X)*(C.Y - B.Y) - (P.Y - B.Y)*(C.X - B.X);
    Double equation3 = (P.X - C.X)*(A.Y - C.Y) - (P.Y - C.Y)*(A.X - C.X);
    if(equation1 > 0 && equation2 > 0 && equation3 > 0) {
        return true;
    } else if(equation1 < 0 && equation2 < 0 && equation3 < 0) {
        return true;
    } else {
        return false;
    }
}

//Point A and B are points who have line connecting them.
//Point P is the point in question to determine which side of the line it resides on
//If result is positive, then the point is on the right of the line
//If result is negative, it is on the left of the line
//If the result is 0, the point resides ON the line
public static Double sideOfLine(Point A, Point B, Point P) {
return ((B.X - A.X)*(P.Y - A.Y)) - ((B.Y - A.Y)*(P.X - A.X));
}

public static void QuickHull(ArrayList<Point> pointList) {
    //ArrayList<Point> Convex_hull = new ArrayList<>();
    Point leftPoint = pointList.get(0); //also known as Point A
    Point rightPoint = pointList.get(pointList.size() - 1); //also known as Point B
    pointList.remove(0);
    pointList.remove(pointList.size() - 1);
    Convex_hull.add(leftPoint);
    Convex_hull.add(rightPoint);
    Double distance = distanceOfPointFromLine(leftPoint, rightPoint, pointList.get(1));
    ArrayList<Point> S1 = new ArrayList<>(); //right side of the line AB
    ArrayList<Point> S2 = new ArrayList<>(); //rightside of the line BA
    for(int i = 0; i < pointList.size(); i++) {
        if(sideOfLine(leftPoint, rightPoint, pointList.get(i)) > 0) {
            S1.add(pointList.get(i));
        } else if(sideOfLine(rightPoint, leftPoint, pointList.get(i)) > 0) {
            S2.add(pointList.get(i));
        }
    }

    Find_hull(S1, leftPoint, rightPoint);
    Find_hull(S2, rightPoint, leftPoint);


}

public static void Find_hull(ArrayList<Point> remainingPoints, Point P, Point Q) {
    if(remainingPoints.isEmpty()) {
        return;
    }
    Point farthestC = new Point();
    Double farthestDistance = distanceOfPointFromLine(P, Q, remainingPoints.get(0));
    for(int i = 0; i < remainingPoints.size(); i++) {
        //Point tempPoint = remainingPoints.get(i);
        Double farthestDistanceTemp = Math.abs(distanceOfPointFromLine(P, Q, remainingPoints.get(i)));
        //need absolute value here possibly to fix issue with not getting correct point
        //when doing Find_hull of list S2, we are getting negative distances, and then trying to check the largest one
        if(farthestDistanceTemp > farthestDistance) {
            farthestC = remainingPoints.get(i);
            farthestDistance = farthestDistanceTemp;
        }

    }
    Convex_hull.add(farthestC);
    ArrayList<Point> S0 = new ArrayList<>();
    ArrayList<Point> S1 = new ArrayList<>();
    ArrayList<Point> S2 = new ArrayList<>();
    for(int i = 0; i < remainingPoints.size(); i++) {
        if(insideTriangle(P, Q, farthestC, remainingPoints.get(i))) {
            S0.add(remainingPoints.get(i));
            //remainingPoints.remove(i);
        } else if(sideOfLine(P, Q, remainingPoints.get(i)) > 0) {
            S1.add(remainingPoints.get(i));
        } else if(sideOfLine(Q, P, remainingPoints.get(i)) > 0) {
            S2.add(remainingPoints.get(i));
        }
    }
    Find_hull(S1, P, farthestC);
    Find_hull(S2, farthestC, Q);
    
}


    public static void main(String[] args) throws Exception {
    System.out.println("Enter file path");
    Scanner scanner = new Scanner(System.in);
    String filepath = scanner.nextLine();
    QuickHull(parseFile.returnPoints(filepath));
    System.out.println(Convex_hull);
    scanner.close();
    }
}

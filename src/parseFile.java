import java.util.*;
import java.io.*;

public class parseFile {
    
    //returns a sorted list of ascending order of X values of the point, as an ArrayList, with each Point
    //being a custom object of type Point(Double X, Double Y)
    public static ArrayList<Point> returnPoints(String filepath) throws IOException {
        ArrayList<Point> pointsList = new ArrayList<>();
        File file = new File(filepath);
        Scanner scannerFile = new Scanner(file);
        while(scannerFile.hasNextLine()) {
            String s = scannerFile.nextLine();
            String[] array = s.split("\\s+");
            Double X = Double.parseDouble(array[0]);
            Double Y = Double.parseDouble(array[1]);
            Point point = new Point(X, Y);
            pointsList.add(point);
        }
        Collections.sort(pointsList);
        scannerFile.close();
        return pointsList;
    }
}

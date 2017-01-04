package augustyn.marcin.encryption.generator;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARCIAUG.
 */
public class HenonMap {
    private static double a = 1.4;
    private static double b = 0.3;
    private static double initial_x = Math.random();
    private static double initial_y = Math.random();
    private static int iterations = 5000;
    private static int excludeFirst = 1000;

    public static List<Point2d> getGeneretedNumberList(){
        List<Point2d> result = new ArrayList<Point2d>();
        result.add(new Point2d(initial_x, initial_y));
        //discard first N results
        for (int i = 0; i < excludeFirst; i++){
            result.set(0,getNextValueForHenonMap(result.get(result.size() - 1)));
        }
        // get next 1000 iterates
        for (int i = 0; i < iterations; i++) {
            result.add(getNextValueForHenonMap(result.get(result.size() - 1)));
        }
        return result;
    }

    private static Point2d getNextValueForHenonMap(Point2d previousPoint) {
        Point2d newValue = new Point2d();
        newValue.x = 1 - (a * Math.pow(previousPoint.x, 2)) + previousPoint.y;
        newValue.y = b * previousPoint.x;
        return newValue;
    }

    public static void setA(double a) {
        HenonMap.a = a;
    }

    public static void setB(double b) {
        HenonMap.b = b;
    }

    public static void setInitial_x(double initial_x) {
        HenonMap.initial_x = initial_x;
    }

    public static void setInitial_y(double initial_y) {
        HenonMap.initial_y = initial_y;
    }

    public static void setIterations(int iterations) {
        HenonMap.iterations = iterations;
    }

    public static void setExcludeFirst(int excludeFirst) {
        HenonMap.excludeFirst = excludeFirst;
    }
}

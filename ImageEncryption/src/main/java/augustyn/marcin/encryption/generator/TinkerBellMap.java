package augustyn.marcin.encryption.generator;

import javax.vecmath.Point2d;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARCIAUG.
 */
public class TinkerBellMap {
    private static double a = 0.9;
    private static double b = -0.6013;
    private static double c = 2.0;
    private static double d = 0.5;
    private static double initial_x = -0.72;
    private static double initial_y = -0.64;
    private static int iterations = 50000;
    private static int excludeFirst = 0;

    public static List<Point2d> getGeneretedNumberList(){
        List<Point2d> result = new ArrayList<Point2d>();
        result.add(new Point2d(initial_x, initial_y));
        //discard first N results
        for (int i = 0; i < excludeFirst; i++){
            result.set(0,getNextValueForTinkerBellMap(result.get(result.size() - 1)));
        }
        // get next 1000 iterates
        for (int i = 0; i < iterations; i++) {
            result.add(getNextValueForTinkerBellMap(result.get(result.size() - 1)));
        }
        return result;
    }
    // compare default value graph with https://en.wikipedia.org/wiki/Tinkerbell_map
    private static Point2d getNextValueForTinkerBellMap(Point2d previousPoint) {
        Point2d newValue = new Point2d();
        newValue.x = Math.pow(previousPoint.x, 2) - Math.pow(previousPoint.y,2) + (a * previousPoint.x) + (b * previousPoint.y);
        newValue.y = (2 * previousPoint.x * previousPoint.y) + (c * previousPoint.x) + (d * previousPoint.y);
        return newValue;
    }

    public static void setA(double a) {
        TinkerBellMap.a = a;
    }

    public static void setB(double b) {
        TinkerBellMap.b = b;
    }

    public static void setC(double c) {
        TinkerBellMap.c = c;
    }

    public static void setD(double d) {
        TinkerBellMap.d = d;
    }

    public static void setInitial_x(double initial_x) {
        TinkerBellMap.initial_x = initial_x;
    }

    public static void setInitial_y(double initial_y) {
        TinkerBellMap.initial_y = initial_y;
    }

    public static void setIterations(int iterations) {
        TinkerBellMap.iterations = iterations;
    }

    public static void setExcludeFirst(int excludeFirst) {
        TinkerBellMap.excludeFirst = excludeFirst;
    }
}

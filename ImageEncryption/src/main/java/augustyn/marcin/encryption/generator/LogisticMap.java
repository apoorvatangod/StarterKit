package augustyn.marcin.encryption.generator;


import edu.princeton.cs.introcs.StdDraw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MARCIAUG.
 */

public class LogisticMap {

    private static double r = 3.90;
    private static double x = Math.random();
    private static int iterations = 1000;
    private static int excludeFirst = 1000;

    public static List<Double> getGeneretedNumberList(){
        List<Double> result = new ArrayList<Double>();
        //discard first N results
        for (int i = 0; i < excludeFirst; i++){
            x = getNextValueForLogisticMap(x);
        }
        // get next 1000 iterates
        for (int i = 0; i < iterations; i++) {
            x = getNextValueForLogisticMap(x);
            result.add(x);
        }
        return result;
    }

    // draw bifurcation diagram - compare with https://en.wikipedia.org/wiki/Logistic_map
    public static void drawBifurcationDiagram() {
        int N = 800;
        StdDraw.clear();
        StdDraw.setXscale(2.8, 4.0);
        StdDraw.setYscale(0.0, 1.0);
        StdDraw.text(3.5,0,"x=f(r)");

        for (double r_temp = 2.8; r_temp <= 4.0; r_temp += 1.2/N) {
            setR(r_temp);
            // choose random initial value
            double x_temp = Math.random();

            // ignore first 1000 iterates
            for (int i = 0; i < 1000; i++)
                x_temp = getNextValueForLogisticMap(x_temp);

            // plot next 100 iterates
            for (int i = 0; i < 100; i++) {
                x_temp = getNextValueForLogisticMap(x_temp);
                StdDraw.point(r_temp, x_temp);
            }

            StdDraw.show(1);
        }
        //reset to default
        setR(3.90);
    }
    private static double getNextValueForLogisticMap(double x1) {
        return r * x1 * (1.0 - x1);
    }

    public static void setR(double r) {
        LogisticMap.r = r;
    }

    public static void setX(double x) {
        LogisticMap.x = x;
    }

    public static void setIterations(int iterations) {
        LogisticMap.iterations = iterations;
    }

    public static void setExcludeFirst(int excludeFirst) {
        LogisticMap.excludeFirst = excludeFirst;
    }
}

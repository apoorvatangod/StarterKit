package augustyn.marcin.encryption.utils;

import edu.princeton.cs.introcs.StdDraw;

import javax.vecmath.Point2d;
import java.util.List;

/**
 * Created by MARCIAUG.
 */
public class Visualisation {
    private static double x_min = 0;
    private static double x_max = 1;
    private static double y_min = 0;
    private static double y_max = 1;

    public static void draw1DResults(List<Double> result){
        StdDraw.clear();
        StdDraw.setXscale(0, result.size());
        StdDraw.setYscale(y_min, y_max);
        for (int i = 0; i < result.size(); i++) {
            StdDraw.point(i, result.get(i));
        }
        StdDraw.show(1);
    }

    public static void draw2DResults(List<Point2d> result){
        StdDraw.clear();
        StdDraw.setXscale(x_min, x_max);
        StdDraw.setYscale(y_min, y_max);
        for (Point2d point: result) {
            StdDraw.point(point.x, point.y);
        }
        StdDraw.show(1);
    }

    public static void setScaleX(double x_min, double x_max) {
        Visualisation.x_min = x_min;
        Visualisation.x_max = x_max;
    }

    public static void setScaleY(double y_min, double y_max) {
        Visualisation.y_min = y_min;
        Visualisation.y_max = y_max;
    }
}

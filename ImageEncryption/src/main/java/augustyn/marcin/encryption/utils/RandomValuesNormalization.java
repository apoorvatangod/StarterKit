package augustyn.marcin.encryption.utils;

import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marcin.
 */
public class RandomValuesNormalization {
    public static List<Integer> normalize(List<Double> randomArray, int numberOfPixels){
        double max = Collections.max(randomArray);
        double min = Collections.min(randomArray);
        List<Integer> result = new ArrayList<Integer>();
        for (Double value : randomArray) {
            Double doubleValue = ((value - min) / (max - min)) * numberOfPixels;
            result.add(doubleValue.intValue());
        }

        return result;
    }
}

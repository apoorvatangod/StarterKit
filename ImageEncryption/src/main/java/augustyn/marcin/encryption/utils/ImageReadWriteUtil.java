package augustyn.marcin.encryption.utils;

import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * Created by Marcin.
 */
public class ImageReadWriteUtil {
    public static Mat read (String path){
        Mat output = imread(path,CV_LOAD_IMAGE_COLOR);
        return output;
    }
    public static void write (Mat image, String path){
        imwrite(path, image);
    }

}

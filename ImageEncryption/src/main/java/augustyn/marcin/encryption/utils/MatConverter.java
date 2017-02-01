package augustyn.marcin.encryption.utils;

import org.opencv.core.Mat;
import org.opencv.core.Point3;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.CvType.CV_8UC3;

/**
 * Created by Marcin.
 */
public class MatConverter {
    public static List<Point3> matToPoint3ArrayList(Mat inputMat){
        List<Point3> result =  new ArrayList<Point3>();

        for(int i = 0; i < inputMat.rows(); i++){
            for(int j = 0; j < inputMat.cols(); j++){
                result.add(new Point3(inputMat.get(i, j)[0], inputMat.get(i, j)[1], inputMat.get(i, j)[2]));
            }
        }
        return result;
    }

    public static Mat point3ArrayListToMat(List<Point3> ipnutList, int rows, int columns){
        Mat result = new Mat(rows, columns, CV_8UC3);
        int elementNumber = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                double [] pixel = new double[3];
                pixel[0] = ipnutList.get(elementNumber).x;
                pixel[1] = ipnutList.get(elementNumber).y;
                pixel[2] = ipnutList.get(elementNumber).z;
                result.put(i, j, pixel);
                elementNumber++;
            }
        }
        return result;
    }
}

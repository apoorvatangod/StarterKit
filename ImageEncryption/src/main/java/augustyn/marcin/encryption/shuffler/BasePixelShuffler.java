package augustyn.marcin.encryption.shuffler;

import augustyn.marcin.encryption.utils.ImageReadWriteUtil;
import augustyn.marcin.encryption.utils.MatConverter;
import org.opencv.core.Mat;
import org.opencv.core.Point3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin.
 */
public abstract class BasePixelShuffler implements PixelShuffler{
    public boolean execute(String inputPath, String outputPath, int iterationNumber) {
        Mat image = ImageReadWriteUtil.read(inputPath);
        int rows = image.rows();
        int cols = image.cols();
        List<Point3> pixelList = generatePixelListFromMat(image);

        List<Integer> randomPixelNumbersInList = getRandomPixelsNumbers(iterationNumber, pixelList.size() - 1);
        for(int i = 0; i < 2 * iterationNumber; i += 2){
            pixelList = switchPixels(pixelList, randomPixelNumbersInList.get(i), randomPixelNumbersInList.get(i + 1));
        }
        Mat image2 = generateMatFromPixelList(pixelList, rows, cols);
        ImageReadWriteUtil.write(image2, outputPath);
        return true;
    }

    private List<Point3> generatePixelListFromMat(Mat imageMat){
        return MatConverter.matToPoint3ArrayList(imageMat);
    }
    private Mat generateMatFromPixelList(List<Point3> imagePixelList, int rows, int columns){
        return MatConverter.point3ArrayListToMat(imagePixelList, rows, columns);
    }
    private List<Point3> switchPixels(List<Point3> imagePixelList, int pixelIndex1, int pixelIndex2){
        Point3 temp = imagePixelList.get(pixelIndex1);
        imagePixelList.set(pixelIndex1, imagePixelList.get(pixelIndex2));
        imagePixelList.set(pixelIndex2, temp);
        return imagePixelList;
    }
    abstract List<Integer> getRandomPixelsNumbers(int numberOfRandomNumbersToGenerate, int numberOfPixels);
}

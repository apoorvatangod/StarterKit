package augustyn.marcin.encryption.main;

import augustyn.marcin.encryption.external.Imshow;
import augustyn.marcin.encryption.generator.HenonMap;
import augustyn.marcin.encryption.generator.LogisticMap;
import augustyn.marcin.encryption.generator.TinkerBellMap;
import augustyn.marcin.encryption.shuffler.LogisticMapPixelShuffler;
import augustyn.marcin.encryption.shuffler.PixelShuffler;
import augustyn.marcin.encryption.utils.Visualisation;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * Created by MARCIAUG.
 */
public class Main {
    public static void main(String[] args) {

//        LogisticMap.drawBifurcationDiagram();
//        Visualisation.draw1DResults(LogisticMap.getGeneretedNumberList());
//
//        HenonMap.drawBifurcationDiagram();
//        Visualisation.setScaleX(-1.5, 1.5);
//        Visualisation.setScaleY(-0.4, 0.4);
//        Visualisation.draw2DResults(HenonMap.getGeneretedNumberList());
//
//		  Visualisation.setScaleX(-1.5, 1);
//        Visualisation.setScaleY(-1.6, 0.6);
//        Visualisation.draw2DResults(TinkerBellMap.getGeneretedNumberList());


        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//        Mat sourceImg =imread("C:/Users/Wacek/Desktop/original.jpg",CV_LOAD_IMAGE_COLOR);
//        Imshow im = new Imshow("Original");
//        im.setResizable(true);
//        im.showImage(sourceImg);

        PixelShuffler shuffler = new LogisticMapPixelShuffler();
        shuffler.execute("C:/Users/Wacek/Desktop/original.jpg", "C:/Users/Wacek/Desktop/lol.jpg", 2000000);

    }
}

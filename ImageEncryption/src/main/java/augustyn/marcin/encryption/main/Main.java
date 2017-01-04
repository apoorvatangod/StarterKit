package augustyn.marcin.encryption.main;

import augustyn.marcin.encryption.generator.HenonMap;
import augustyn.marcin.encryption.generator.LogisticMap;
import augustyn.marcin.encryption.utils.Visualisation;

/**
 * Created by MARCIAUG.
 */
public class Main {
    public static void main(String[] args) {
        // LogisticMap.drawBifurcationDiagram();
        // Visualisation.draw1DResults(LogisticMap.getGeneretedNumberList());

        Visualisation.setScaleY(-0.4, 0.4);
        Visualisation.setScaleX(-1.5, 1.5);
        Visualisation.draw2DResults(HenonMap.getGeneretedNumberList());
    }
}

package augustyn.marcin.encryption.main;

import augustyn.marcin.encryption.generator.HenonMap;
import augustyn.marcin.encryption.generator.LogisticMap;
import augustyn.marcin.encryption.generator.TinkerBellMap;
import augustyn.marcin.encryption.utils.Visualisation;

/**
 * Created by MARCIAUG.
 */
public class Main {
    public static void main(String[] args) {
        // LogisticMap.drawBifurcationDiagram();
        // Visualisation.draw1DResults(LogisticMap.getGeneretedNumberList());

        // HenonMap.drawBifurcationDiagram();
        // Visualisation.setScaleX(-1.5, 1.5);
        // Visualisation.setScaleY(-0.4, 0.4);
        // Visualisation.draw2DResults(HenonMap.getGeneretedNumberList());
		Visualisation.setScaleX(-1.5, 1);
        Visualisation.setScaleY(-1.6, 0.6);
        Visualisation.draw2DResults(TinkerBellMap.getGeneretedNumberList());

    }
}

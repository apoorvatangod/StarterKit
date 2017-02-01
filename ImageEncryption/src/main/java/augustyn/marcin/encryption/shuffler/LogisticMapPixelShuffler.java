package augustyn.marcin.encryption.shuffler;

import augustyn.marcin.encryption.generator.LogisticMap;
import augustyn.marcin.encryption.utils.RandomValuesNormalization;

import java.util.List;

/**
 * Created by Marcin.
 */
public class LogisticMapPixelShuffler extends BasePixelShuffler{
    List<Integer> getRandomPixelsNumbers(int numberOfRandomNumbersToGenerate, int numberOfPixels) {
        LogisticMap.setIterations(2 * numberOfRandomNumbersToGenerate);
        return RandomValuesNormalization.normalize(LogisticMap.getGeneretedNumberList(), numberOfPixels);
    }
}

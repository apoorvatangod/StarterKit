package augustyn.marcin.stockmarket.main;

import java.util.Random;

public class Randomizer {
	private static Random random = new Random();
	
	public static float randomFloat(float lowerBound, float upperBound){
		return lowerBound + random.nextFloat() * (upperBound - lowerBound);
	}
}

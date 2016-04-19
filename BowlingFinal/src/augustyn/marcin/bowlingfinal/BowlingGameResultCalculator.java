package augustyn.marcin.bowlingfinal;

public interface BowlingGameResultCalculator {

	/**
	 * Register a thrown a ball.
	 * @param numberOfPins number of knocked down pins
	 * @throws UnsupportedOperationException - when try to roll and game is over
	 */
	public void roll(int numberOfPins);

	/**
	 * @return current game score
	 */
	public int score();

	/**
	 * @return true if a game is over, otherwise false
	 */
	public boolean isFinished();
}

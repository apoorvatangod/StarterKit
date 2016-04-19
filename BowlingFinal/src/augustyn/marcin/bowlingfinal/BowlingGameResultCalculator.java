package augustyn.marcin.bowlingfinal;

public interface BowlingGameResultCalculator {

	/**
	 * Register a thrown a ball.
	 * @param numberOfPins number of knocked down pins
	 * @throws UnsupportedOperationException - when try to roll and game is over
	 * @throws IllegalArgumentException - when numberOfPins is greater then 10
	 * @throws IllegalArgumentException - when sum of two rolls in same round is greater than 10
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

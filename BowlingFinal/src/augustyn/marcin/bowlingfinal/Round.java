package augustyn.marcin.bowlingfinal;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Round {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Round.class);
	private static final int PINS_FOR_STRIKE = 10;
	private static final int MAX_PINS_IN_ONE_ROUND = 10;
	private int roundNumber;
	protected List<Roll> rolls = new ArrayList<>();
	private Round nextRound;
	
	public Round(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	/**
	 * Gives bonus points when requested by previous round.
	 * @param bonus - type of bonus: SPARE/STRIKE
	 * @return value of bonus points
	 */
	public int giveBonus(Bonus bonus){
		int bonusValue = 0;
		if (bonus == Bonus.SPARE){
			bonusValue = rolls.get(0).getRollValue();
		}
		if (bonus == Bonus.STRIKE && rolls.get(0).getRollValue() == PINS_FOR_STRIKE){
			bonusValue = isThereNextRound() ? nextRound.giveBonus(Bonus.SPARE) + PINS_FOR_STRIKE : PINS_FOR_STRIKE;
		}
		if(bonus == Bonus.STRIKE && getNumberOfRollsInRound() >= 1 && rolls.get(0).getRollValue() != PINS_FOR_STRIKE){
			bonusValue = rolls.get(0).getRollValue();
			bonusValue = getNumberOfRollsInRound() == 2 ? bonusValue + rolls.get(1).getRollValue() : bonusValue;
		}
		return bonusValue;
	}
	/**
	 * Checkes if round has been finished.
	 * @return true if round is finished
	 */
	public boolean checkIfRoundFinished(){
		return getNumberOfRollsInRound() > 0 && (rolls.get(0).getRollValue() == PINS_FOR_STRIKE || 
				getNumberOfRollsInRound() == 2);
	}
	/**
	 * Gives total score for this round (with bonus)
	 * @return total sum of points for a round
	 */
	public int getScore() {
		int score = 0;
		for(Roll roll : rolls){
			score += roll.getRollValue();
		}
		if(isThereNextRound() && checkIfRoundFinished() && getNumberOfRollsInRound() == 1){
			score += nextRound.giveBonus(Bonus.STRIKE);
		}
		if(isThereNextRound() && getNumberOfRollsInRound() == 2 && rolls.get(0).getRollValue() + 
				rolls.get(1).getRollValue() == MAX_PINS_IN_ONE_ROUND){
			score += nextRound.giveBonus(Bonus.SPARE);
		}
		return score;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
	/**
	 * Adds new roll for a round.
	 * @param numberOfPins - number of pins of new roll
	 * @throws IllegalArgumentException if roll is not an int 0-10
	 * @throws IllegalArgumentException if sum of two rolls is bigger than 10
	 */
	public void addRoll(int numberOfPins){
		if(!validateSingleRoll(numberOfPins)){
			throw new IllegalArgumentException("Illegal argument in roll. Roll must be 0-10.");
		}
		if(getNumberOfRollsInRound() != 0 && !validateRollsInRound(numberOfPins)){
			throw new IllegalArgumentException("Illegal argument in roll. Sum of rolls in round bigger than 10.");
		}
		rolls.add(new Roll(numberOfPins));
	}
	protected int getNumberOfRollsInRound(){
		return rolls.size();
	}
	public void setNextRound(Round nextRound) {
		this.nextRound = nextRound;
	}
	/**
	 * Checks if there is next round.
	 * @return false if this round is last one
	 */
	public boolean isThereNextRound() {
		return nextRound != null ? true : false;
	}
	/**
	 * Validator for a single roll.
	 * @param numberOfPins - numbers of pins to be added to list of rolls
	 * @return true if numberOfPins is a valid roll
	 */
	private boolean validateSingleRoll(int numberOfPins){
		return numberOfPins >= 0 && numberOfPins <= PINS_FOR_STRIKE;
	}
	/**
	 * Validator for all rolls in round.
	 * @param numberOfPins - numbers of pins to be added to list of rolls
	 * @return true if numberOfPins is a valid roll
	 */
	protected boolean validateRollsInRound(int numberOfPins){
		return rolls.get(0).getRollValue() + numberOfPins <= MAX_PINS_IN_ONE_ROUND;
	}
}

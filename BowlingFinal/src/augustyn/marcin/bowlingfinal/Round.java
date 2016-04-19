package augustyn.marcin.bowlingfinal;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Round {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Round.class);
	private int roundNumber;
	protected List<Roll> rolls = new ArrayList<>();
	private Round nextRound;
	
	public Round(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public int giveBonus(Bonus bonus){
		int bonusValue = 0;
		if (bonus == Bonus.SPARE && getNumberOfRollsInRound() > 0){
			bonusValue = rolls.get(0).getRollValue();
		}
		if (bonus == Bonus.STRIKE && rolls.get(0).getRollValue() == 10){
			bonusValue = isThereNextRound() ? nextRound.giveBonus(Bonus.SPARE) + 10 : 10;
		}
		if(bonus == Bonus.STRIKE && getNumberOfRollsInRound() >= 1 && rolls.get(0).getRollValue() != 10){
			bonusValue = rolls.get(0).getRollValue();
			bonusValue = getNumberOfRollsInRound() == 2 ? bonusValue + rolls.get(1).getRollValue() : bonusValue;
		}
		return bonusValue;
	}

	public boolean checkIfRoundFinished(){
		return rolls.size() > 0 && (rolls.get(0).getRollValue() == 10 || rolls.size() == 2);
	}
	
	public int getScore() {
		int score = 0;
		for(Roll roll : rolls){
			score += roll.getRollValue();
		}
		if(isThereNextRound() && checkIfRoundFinished() && rolls.size() == 1){
			score += nextRound.giveBonus(Bonus.STRIKE);
		}
		if(isThereNextRound() && checkIfRoundFinished() && rolls.size() == 2 && 
				rolls.get(0).getRollValue() + rolls.get(1).getRollValue() == 10){
			score += nextRound.giveBonus(Bonus.SPARE);
		}
		return score;
	}
	public int getRoundNumber() {
		return roundNumber;
	}
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
	public boolean isThereNextRound() {
		return nextRound != null ? true : false;
	}
	private boolean validateSingleRoll(int numberOfPins){
		return numberOfPins >= 0 && numberOfPins <= 10;
	}
	protected boolean validateRollsInRound(int numberOfPins){
		return rolls.get(0).getRollValue() + numberOfPins <= 10;
	}
}

package augustyn.marcin.bowlingfinal;

public class LastRound extends Round {

	public LastRound() {
		super(10);
	}

	@Override
	public boolean checkIfRoundFinished() {
		boolean twoRollsNoBonus = getNumberOfRollsInRound() == 2 && getScore() < 10;
		boolean threeRolls = getNumberOfRollsInRound() == 3;
		return twoRollsNoBonus || threeRolls;
	}

	@Override
	public int getScore() {
		int score = 0;
		for(Roll roll : rolls){
			score += roll.getRollValue();
		}
		return score;
	}

	@Override
	public int giveBonus(Bonus bonus) {
		int bonusValue = 0;
		if (bonus == Bonus.SPARE){
			bonusValue = rolls.get(0).getRollValue();
		}

		if(getNumberOfRollsInRound() >= 1 && bonus == Bonus.STRIKE){
			bonusValue = rolls.get(0).getRollValue();
			bonusValue = getNumberOfRollsInRound() > 1 ? bonusValue + rolls.get(1).getRollValue() : bonusValue;
		}
		return bonusValue;
	}

	@Override
	protected boolean validateRollsInRound(int numberOfPins) {
		boolean invalidSumWhenTwoRolls = getNumberOfRollsInRound() == 1 && 
				(rolls.get(0).getRollValue() + numberOfPins > 10 && rolls.get(0).getRollValue() != 10);
		
		boolean invalidSumWhenThreeRolls = getNumberOfRollsInRound() == 2 && rolls.get(0).getRollValue() + 
				rolls.get(1).getRollValue() + numberOfPins > 30;
				
		return !(invalidSumWhenTwoRolls || invalidSumWhenThreeRolls);
	}
}

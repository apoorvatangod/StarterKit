package augustyn.marcin.bowlingfinal;

public class LastRound extends Round {

	@Override
	public boolean checkIfRoundFinished() {
		boolean twoRollsNoBonus = getNumberOfRollsInRound() == 2 && getScore() < 10;
		boolean threeRolls = getNumberOfRollsInRound() == 3;
		return twoRollsNoBonus || threeRolls;
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
		return getNumberOfRollsInRound() != 1 || rolls.get(0).getRollValue() + numberOfPins <= 10 || 
				rolls.get(0).getRollValue() == 10;
	}
}

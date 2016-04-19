package augustyn.marcin.bowling;

import java.util.ArrayList;

/**
 * Counts score of bowling game for player defined by playerId. History of
 * player's rolls is stored as array of SingleRoll objects.
 * 
 * @author MARCIAUG
 *
 */
public class ScoreCalculator implements BowlingGameResultCalculator {
	private int playerId;
	private ArrayList<SingleRoll> rolls = new ArrayList<>();

	public ScoreCalculator(int playerId) {
		this.playerId = playerId;
	}
	
	@Override
	public void roll(int numberOfPins) {
		// when try to execute roll after game is over
		if (isFinished()) {
			throw new UnsupportedOperationException("Game is over!");
		}
		int totalNumberOfRolls = rolls.size();
		// check if number of pins is legal number
		if (numberOfPins < 0 || numberOfPins > 10) {
			throw new IllegalArgumentException("Illegal argument in roll(int numberOfPins). Must be int 0-10.");
		}
		/** getting round number **/
		int round = 1;
		if (totalNumberOfRolls > 0) {
			round = rolls.get(totalNumberOfRolls - 1).getRound();
			// go to next round when 10 is rolled
			if (rolls.get(totalNumberOfRolls - 1).getRoll() == 10) {
				round++;
			} else {
				if (totalNumberOfRolls > 1) {
					// go to next round when it is second roll
					if (isItNotTheFirstRollInRound(totalNumberOfRolls - 1)) {
						round++;
					}
				}
			}
		}
		/** getting bonus count **/
		int bonus = 0;
		if (totalNumberOfRolls > 0) {
			boolean wasLastRollTheSameRoundAsNewRoll = rolls.get(totalNumberOfRolls - 1).getRound() == round;
			int sumOfRollsFromLastTwoRolls = rolls.get(totalNumberOfRolls - 1).getRoll() + numberOfPins;
			// when two rolls in one round sums up to more then 10
			if (wasLastRollTheSameRoundAsNewRoll && sumOfRollsFromLastTwoRolls > 10) {
				throw new IllegalArgumentException("Illegal sum of points in one round.");
			}
			// when second roll in round is strike
			if (wasLastRollTheSameRoundAsNewRoll && sumOfRollsFromLastTwoRolls == 10) {
				bonus = round > 10 ? 0 : 1;
			}
		}
		// when first roll in round is strike, no bonus if in last round was
		// strike, strike
		if (numberOfPins == 10 && bonus == 0) {
			bonus = round > 10 ? 0 : 2;
		}
		rolls.add(new SingleRoll(numberOfPins, round, bonus));
		System.out.println("New roll added:" + numberOfPins + " pins, round " + round + ", bonus " + bonus);
	}

	@Override
	public int score() {
		int score = 0;
		for (int i = 0; i < rolls.size(); i++) {
			// add roll to score without bonus, rolls from bonus round(s) dont count
			score = rolls.get(i).getRound() > 10 ? score : score + rolls.get(i).getRoll();
			if (rolls.get(i).getBonus() > 0) {
				// if it is not last roll, add bonus (if apply) from next roll
				if (i < rolls.size() - 1) {
					score += rolls.get(i + 1).getRoll();
				}
				// same but for double bonus
				if ((i < rolls.size() - 2) && (rolls.get(i).getBonus() == 2)) {
					score += rolls.get(i + 2).getRoll();
				}
			}
		}
		return score;
	}

	@Override
	public boolean isFinished() {
		int indexOfLastRoll = rolls.size() - 1;
		if (indexOfLastRoll > 1) {
			// true if there where already 3 rolls in last round eg. 5(round 10),5(r 10),1(r 11)
			boolean wasThereThirdRollAfterStrikeInSecondRollInLastRound = rolls.get(indexOfLastRoll).getRound() >= 11
					&& rolls.get(indexOfLastRoll).getBonus() == 0;
			// true if first two rolls in last round were 10 (r 10), (r 11) -
			// should not end then, 1 roll remaining
			boolean wareTwoFirstRollsInLastRoundTens = rolls.get(indexOfLastRoll).getRoll() == 10
					&& rolls.get(indexOfLastRoll - 1).getRoll() == 10;
			// additionaly if round is 12 ends game eg. 10 (r 10), 10 (r 11), 10 (r 12)
			boolean wasThereThirdRollInLastRound = (wasThereThirdRollAfterStrikeInSecondRollInLastRound
					&& !wareTwoFirstRollsInLastRoundTens) || rolls.get(indexOfLastRoll).getRound() > 11;
			// true if no bonus round eg of last round: 4(r 10),5(r 10)
			boolean wasThereNoBonusRound = rolls.get(indexOfLastRoll).getRound() >= 10
					&& rolls.get(indexOfLastRoll).getBonus() == 0 && isItNotTheFirstRollInRound(indexOfLastRoll);
			if (wasThereThirdRollInLastRound || wasThereNoBonusRound) {
				return true;
			}
		}
		return false;
	}

	class SingleRoll {
		private int roll;
		private int round;
		private int bonus;

		public SingleRoll(int roll, int round, int bonus) {
			this.roll = roll;
			this.round = round;
			this.bonus = bonus;
		}

		public int getRoll() {
			return roll;
		}

		public int getRound() {
			return round;
		}

		public int getBonus() {
			return bonus;
		}

	}

	public int getPlayerId() {
		return playerId;
	}

	// check if current and previous rolls were in the same round
	private boolean isItNotTheFirstRollInRound(int index) {
		int totalNumberOfRolls = rolls.size();
		if (totalNumberOfRolls > 1 && (rolls.get(index).getRound() == rolls.get(index - 1).getRound())) {
			return true;
		}
		return false;
	}

}

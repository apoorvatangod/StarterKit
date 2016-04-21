package augustyn.marcin.bowlingfinal;

import java.util.LinkedList;
import java.util.List;

public class Game implements BowlingGameResultCalculator {
	private List<Round> rounds = new LinkedList<>();
	
	public Game() {
		rounds.add(new Round());
	}

	@Override
	public void roll(int numberOfPins) throws BowlingException {
		if(isFinished()){
			throw new BowlingException("Cannot roll when game is over!");
		}
		if(rounds.get(rounds.size() - 1).checkIfRoundFinished()){
			int roundNumber = rounds.size() + 1;
			if(roundNumber == 10){
				rounds.add(new LastRound());
			}
			else{
				rounds.add(new Round());
			}
			
			Round previousRound = rounds.get(roundNumber - 2); 
			previousRound.setNextRound(rounds.get(roundNumber - 1));
		}
		rounds.get(rounds.size() - 1).addRoll(numberOfPins);
	}

	@Override
	public int score() {
		int score = 0;
		for (Round round : rounds){
			score += round.getScore();
		}
		return score;
	}

	@Override
	public boolean isFinished() {
		return rounds.size() == 10 && rounds.get(rounds.size() - 1).checkIfRoundFinished();
	}
}

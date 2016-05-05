package augustyn.marcin.pokerfinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Poker {
	private static final Logger logger = LogManager.getLogger(CompareHands.class);
	private final int CARDS_PER_GAME = 10;
	private final int CARDS_IN_HAND = 5;
	private int readLineNumberCounter = 0;
	/**
	 * Reads all hands(games) from file and compare hands in all games.
	 * @return how many times 1st player won
	 * @throws PokerException 
	 */
	public int playAllGames() throws PokerException{		
		Converter converter = new Converter();
		List<Card> allCards = converter.convertFromFile();
		int totalNumberOfGamesRead = converter.getNumberOfGames(allCards);
		
		int winNumberFirstPlayer = 0;
		CompareHands comparator = new CompareHands();
		
		while(readLineNumberCounter != totalNumberOfGamesRead){
			List<List<Card>> hands = getHandsForNextRound(allCards);
			if(doesFirstHandWin(comparator, hands)){
				winNumberFirstPlayer++;
			}
		}
		logger.info("Player 1 won " + winNumberFirstPlayer + " times.");
		return winNumberFirstPlayer;
	}
	
	private List<List<Card>> getHandsForNextRound(List<Card> allCards){
		List<List<Card>> hands = new ArrayList<>();
		//add two hands
		hands.add(new ArrayList<Card>());
		hands.add(new ArrayList<Card>());
		for(int i = 0; i < CARDS_PER_GAME; i++){
			if(i < CARDS_IN_HAND){
				hands.get(0).add(allCards.get(CARDS_PER_GAME * readLineNumberCounter + i));
			}
			else{
				hands.get(1).add(allCards.get(CARDS_PER_GAME * readLineNumberCounter + i));
			}
		}
		readLineNumberCounter++;	
		return hands;
	}
	
	private boolean doesFirstHandWin(CompareHands comparator, List<List<Card>> hands){
		int score1 = comparator.getScoreForHand(hands.get(0));
		int score2 = comparator.getScoreForHand(hands.get(1));
		if(score1 > score2){//TODO jesli tozsame to settle draw
			return true;
		}
		if(score1 < score2){
			return false;
		}
		return settleDraw(comparator, hands);
	}
	
	private boolean settleDraw(CompareHands comparator, List<List<Card>> hands){
		List<Map.Entry<Figures, Integer>> sortedList1 = comparator.sortHand(hands.get(0));
		List<Map.Entry<Figures, Integer>> sortedList2 = comparator.sortHand(hands.get(1));
		for(int i = sortedList1.size() - 1; i >= 0; i--){
			if(sortedList1.get(i).getKey().getValue() != sortedList2.get(i).getKey().getValue()){
				return sortedList1.get(i).getKey().getValue() > sortedList2.get(i).getKey().getValue();
			}
		}
		return false;
	}
}

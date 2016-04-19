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
	private List<Card> allCards = new ArrayList<>();
	private List<Card> hand1 = new ArrayList<>();
	private List<Card> hand2 = new ArrayList<>();
	private int readLineNumberCounter = 0;
	
	public int playAllGames(){
		int winNumberFirstPlayer = 0;
		int totalNumberOfGamesRead = readAllCards();
		CompareHands comparator = new CompareHands();
		
		while(readLineNumberCounter != totalNumberOfGamesRead){
			getHandsForNextRound();
			if(doesFirstHandWin(comparator)){
				winNumberFirstPlayer++;
			}
		}
		logger.info("Player 1 won " + winNumberFirstPlayer + " times.");
		return winNumberFirstPlayer;
	}
	
	private int readAllCards(){
		Converter converter = new Converter();
		allCards = converter.convertFromFile();
		return converter.getNumberOfGames();
	}
	private void getHandsForNextRound(){
		hand1.clear();
		hand2.clear();
		for(int i = 0; i < CARDS_PER_GAME; i++){
			if(i < CARDS_IN_HAND){
				hand1.add(allCards.get(CARDS_PER_GAME * readLineNumberCounter + i));
			}
			else{
				hand2.add(allCards.get(CARDS_PER_GAME * readLineNumberCounter + i));
			}
		}
		readLineNumberCounter++;		
	}
	
	private boolean doesFirstHandWin(CompareHands comparator){
		int score1 = comparator.findValueForColor(hand1) + comparator.findValueForQuantitySets(hand1) + 
				comparator.findValueForStraight(hand1);
		int score2 = comparator.findValueForColor(hand2) + comparator.findValueForQuantitySets(hand2) + 
				comparator.findValueForStraight(hand2);
		if(score1 > score2){
			return true;
		}
		if(score1 < score2){
			return false;
		}
		return settleDraw(comparator);
	}
	
	private boolean settleDraw(CompareHands comparator){
		List<Map.Entry<Integer, Integer>> sortedList1 = comparator.sortHand(hand1);
		List<Map.Entry<Integer, Integer>> sortedList2 = comparator.sortHand(hand2);
		for(int i = sortedList1.size() - 1; i >= 0; i--){
			if(sortedList1.get(i).getKey().intValue() > sortedList2.get(i).getKey().intValue()){
				return true;
			}
			if(sortedList1.get(i).getKey().intValue() < sortedList2.get(i).getKey().intValue()){
				return false;
			}
		}
		return false;
	}
}

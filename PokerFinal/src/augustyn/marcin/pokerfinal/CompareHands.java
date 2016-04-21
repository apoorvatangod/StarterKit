package augustyn.marcin.pokerfinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareHands {
	private static final int NUMBER_OF_COMPARISONS_FOR_FINDING_COLOR = 4;
	private static final int FIRST_LAST_CARD_VALUE_DIFFERENCE_FOR_STRAIGHT = 4;
	private static final int CARDS_IN_HAND = 5;

	/**
	 * Checks if set is color for a hand.
	 * @param hand - list of 5 to be checked
	 * @return value(5) if hand has color, 0 if not
	 */
	public int findValueForColor(List<Card> hand){
		for(int i = 0; i < NUMBER_OF_COMPARISONS_FOR_FINDING_COLOR; i++){
			Shapes shapeCard = hand.get(i).getShape();
			Shapes shapeNextCard = hand.get(i + 1).getShape();
			if(hand.size() != CARDS_IN_HAND || !shapeCard.equals(shapeNextCard)){
				return 0;
			}
		}
		return Sets.FLUSH.getValue();
	}
	
	private Map<Figures,Integer> mapCards(List<Card> hand){
		Map<Figures,Integer> figuresQuantity = new HashMap<>();
		
		for(Card card : hand){
			Figures figure = card.getFigure();
			if(figuresQuantity.get(figure) != null){
				Integer currentQuantity = figuresQuantity.get(figure);
				figuresQuantity.put(figure, currentQuantity + 1);
			}
			else{
				figuresQuantity.put(figure, 1);
			}
		}
		return figuresQuantity;
	}
	/**
	 * Finds value for quantity sets: high card, pair, two pairs, three and four of kind and full house. 
	 * @param hand - list of 5 to be checked
	 * @return value of a set eg full house = 6, four of kind = 7.
	 */
	public int findValueForQuantitySets(List<Card> hand){
		Map<Figures,Integer> figuresQuantity = mapCards(hand);
		int valueOfSet = Sets.HIGH_CARD.getValue();
		if(figuresQuantity.containsValue(4)){
			valueOfSet = Sets.FOUR_OF_KIND.getValue();
		}
		if(figuresQuantity.containsValue(3) && !figuresQuantity.containsValue(2)){
			valueOfSet = Sets.THREE_OF_KIND.getValue();
		}
		if(figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2)){
			valueOfSet = Sets.FULL_HOUSE.getValue();
		}
		if(!figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2) && figuresQuantity.size() == 3){
			valueOfSet = Sets.TWO_PAIRS.getValue();
		}
		if(!figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2) && figuresQuantity.size() == 4){
			valueOfSet = Sets.PAIR.getValue();
		}
		return valueOfSet;
		/*for(Map.Entry<Integer,Integer> entry : figuresQuantity.entrySet()) {
			Integer value = entry.getValue();
			if(value < 3){//so high card will return 0, one pair return 1 and two pairs return 2
				valueOfSet += value - 1;
			}
			else{
				valueOfSet = (value == 4 ? FOUR_OF_KIND : valueOfSet + value);
			}
		}
		if(valueOfSet == 4){//set value for full house
			valueOfSet = FULL_HOUSE;
		}
		return valueOfSet;*/
	}
	/**
	 * Sorts cards in hand first by quantity of particular figures, then by figure value
	 * @param hand - list of 5 to be sorted
	 * @return List of map entries sorted by value then by key
	 */
	public List<Map.Entry<Figures, Integer>> sortHand(List<Card> hand) {
		Map<Figures,Integer> figuresQuantity = mapCards(hand);
		List<Map.Entry<Figures, Integer>> list = new ArrayList<Map.Entry<Figures, Integer>>(figuresQuantity.entrySet());
		Collections.sort(list, new ValueThenKeyComparator<Figures, Integer>());
		return list;
	}
	/**
	 * Checks if there is straight in hand.
	 * @param hand - list of 5 to be checked
	 * @return value for straight(4) if there is straight or 0 if not
	 */
	public int findValueForStraight(List<Card> hand){
		List<Map.Entry<Figures, Integer>> sortedList = sortHand(hand);
		if(sortedList.size() == CARDS_IN_HAND && sortedList.get(sortedList.size() - 1).getKey().getValue() - 
				sortedList.get(0).getKey().getValue() == FIRST_LAST_CARD_VALUE_DIFFERENCE_FOR_STRAIGHT){
			return Sets.STRAIGHT.getValue();
		}
		return 0;
	}	
	/**
	 * Gives set score (level) of hand.
	 * @param hand - 5 card list to be scored
	 * @return - score of set
	 */
	public int getScoreForHand(List<Card> hand){
		return findValueForColor(hand) + findValueForQuantitySets(hand) + findValueForStraight(hand);
	}
}
//source: http://stackoverflow.com/questions/3074154/sorting-a-hashmap-based-on-value-then-key
class ValueThenKeyComparator<K extends Comparable<? super K>,V extends Comparable<? super V>> 
implements Comparator<Map.Entry<K, V>> {

	public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
		int cmp1 = o1.getValue().compareTo(o2.getValue());
		if (cmp1 != 0) {
			return cmp1;
		} else {
			return o1.getKey().compareTo(o2.getKey());
		}
	}
}
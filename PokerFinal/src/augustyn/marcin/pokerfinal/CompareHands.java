package augustyn.marcin.pokerfinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareHands {
	private static final int HIGH_CARD = 0;
	private static final int PAIR = 1;
	private static final int TWO_PAIRS = 2;
	private static final int THREE_OF_KIND = 3;
	private static final int STRAIGHT = 4;
	private static final int FLUSH = 5;
	private static final int FULL_HOUSE = 6;
	private static final int FOUR_OF_KIND = 7;
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
			if(hand.size() != CARDS_IN_HAND || !hand.get(i).getShape().equals(hand.get(i + 1).getShape())){
				return 0;
			}
		}
		return FLUSH;
	}
	
	private Map<Integer,Integer> mapCards(List<Card> hand){
		Map<Integer,Integer> figuresQuantity = new HashMap<>();
		
		for(Card card : hand){
			Integer figure = card.getFigure();
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
		Map<Integer,Integer> figuresQuantity = mapCards(hand);
		int valueOfSet = HIGH_CARD;
		if(figuresQuantity.containsValue(4)){
			valueOfSet = FOUR_OF_KIND;
		}
		if(figuresQuantity.containsValue(3) && !figuresQuantity.containsValue(2)){
			valueOfSet = THREE_OF_KIND;
		}
		if(figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2)){
			valueOfSet = FULL_HOUSE;
		}
		if(!figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2) && figuresQuantity.size() == 3){
			valueOfSet = TWO_PAIRS;
		}
		if(!figuresQuantity.containsValue(3) && figuresQuantity.containsValue(2) && figuresQuantity.size() == 4){
			valueOfSet = PAIR;
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
	public List<Map.Entry<Integer, Integer>> sortHand(List<Card> hand) {
		Map<Integer,Integer> figuresQuantity = mapCards(hand);
		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(figuresQuantity.entrySet());
		Collections.sort(list, new ValueThenKeyComparator<Integer, Integer>());
		return list;
	}
	/**
	 * Checks if there is straight in hand.
	 * @param hand - list of 5 to be checked
	 * @return value for straight(4) if there is straight or 0 if not
	 */
	public int findValueForStraight(List<Card> hand){
		List<Map.Entry<Integer, Integer>> sortedList = sortHand(hand);
		if(sortedList.size() == CARDS_IN_HAND && sortedList.get(sortedList.size() - 1).getKey() - 
				sortedList.get(0).getKey() == FIRST_LAST_CARD_VALUE_DIFFERENCE_FOR_STRAIGHT){
			return STRAIGHT;
		}
		return 0;
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
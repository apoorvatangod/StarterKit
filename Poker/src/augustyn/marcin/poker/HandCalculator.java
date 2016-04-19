package augustyn.marcin.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Class for calculating hand rank for given card set.
 * 
 * List<Card> hand - represents cards (hand) of a player
 * Map<Integer,Integer> figuresQuantity - maps quantity of cards of particular figure in hand
 * Integer[] pairs - stores number of pairs in hand and which figures are they
 * Integer[] triplet - stores number of triplets in hand (0 or 1) and what figure is it
 * Integer[] quartet - stores number of quartets in hand (0 or 1) and what figure is it
 * Integer handLevel - level of set (hand rank), eg. 0-high card, 1 - pair, 2 - two pairs, ...
 * Long handPower - power of a set/hand (used to settle draws when both players have same hand rank)
 *  
 * @author MARCIAUG
 *
 */
public class HandCalculator {
	private Integer playerId;
	private List<Card> hand = new ArrayList<>();
	private Map<Integer,Integer> figuresQuantity = new TreeMap<>();
	private Integer[] pairs = new Integer[3];
	private Integer[] triplet = new Integer[2];
	private Integer[] quartet = new Integer[2];
	private Integer handLevel = -1;
	private Long handPower = -1L;
	
	/**
	 * Constructor
	 * @param playerId - ID of a player
	 */
	public HandCalculator(Integer playerId) {
		this.playerId = playerId;
	}
	
	public Integer getPlayerId() {
		return playerId;
	}

	public Integer getHandLevel() {
		return handLevel;
	}

	public Long getHandPower() {
		return handPower;
	}

	public List<Card> getHand() {
		return hand;
	}
	
	/**
	 * 
	 * @param figure figure of a card from 2-14(Ace)
	 * @param colour color of a card S-Spades, H-Hearts, ...
	 * @return nothing
	 * @throws IllegalArgumentException if colour is different than S,H,D or C
	 * @throws IllegalArgumentException if figure is different than 2-14
	 * @throws UnsupportedOperationException when one tries to pick more than 5 cards
	 */
	public void pickCard(Integer figure, String colour){
		Integer color;
		switch(colour){
		case "S":
		case "1"://spades
			color = 1;
			break;
		case "H":
		case "2"://hearts
			color = 2;
			break;
		case "D":
		case "3"://diamonds
			color = 3;
			break;
		case "C":
		case "4"://clubs
			color = 4;
			break;
		default:
			throw new IllegalArgumentException("Illegal argument in pickCard. Card color can be only: S, H, D, C.");
		}
		if(figure < 2 || figure > 14){
			throw new IllegalArgumentException("Illegal argument in pickCard. Card figure must be 2-14.");
		}
		if(hand.size() == 5){
			throw new UnsupportedOperationException("Cannot pick more than 5 cards by one player.");
		}
		hand.add(new Card(figure, color));
	}
	/**
	 * Shows cards in a hand
	 */
	public void showHand(){
		for (int i = 0; i < hand.size(); i++){
			System.out.println(hand.get(i).toString());
		}
	}
	/**
	 * Shows mapping of	particular figures' quantity
	 */
	public void showCardsMapping(){
		for(Map.Entry<Integer,Integer> entry : figuresQuantity.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();

			  System.out.println(key + " => " + value);
		}
	}
	/**
	 * Maps quantity of figures
	 */
	public void mapCards(){
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
	}
	/**
	 * Finds quantity sets of cards like pair, two pair, three of kind, four of kind
	 */
	public void findQuantitySet(){
		boolean noPairsFound = true;
		pairs[0] = 0;
		triplet[0] = 0;
		quartet[0] = 0;
		for(Map.Entry<Integer,Integer> entry : figuresQuantity.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			
			switch(value){
			case 2://pairs
				pairs[0]++;
				if(noPairsFound){
					pairs[1] = key;
					noPairsFound = false;
				}
				else{
					pairs[2] = key;
				}
				break;
			case 3://triplet
				triplet[0]++;
				triplet[1] = key;
				break;
			case 4://quartet
				quartet[0]++;
				quartet[1] = key;
				break;
			}	
		}
		System.out.println("Pairs: " + pairs[0] + " Triplet:  " + triplet[0] + " Quartet: " + quartet[0]);
	}
	/**
	 * Checks if there is Straight set in hand
	 * @return 1-if there is straight, 0 - no straight
	 */
	public boolean findStaight(){
		if(pairs[0] + triplet[0] + quartet[0] == 0){
			for (int i = 1; i < hand.size(); i++){
				if(hand.get(i).getFigure() - 1 != hand.get(i - 1).getFigure()){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * checks if there is color set in hand
	 * @return 1-if there is color, 0 - no color
	 */
	public boolean findColor(){
		for (int i = 1; i < hand.size(); i++){
			if(hand.get(i).getColor() != hand.get(i - 1).getColor()){
				return false;
			}
		}
		return true;
	}
	/**
	 * Calculates level of set (hand rank), eg. 0-high card, 1 - pair, 2 - two pairs, ...
	 */
	public void calculateSetLevel(){
		sortCards();
		mapCards();
		findQuantitySet();
		if(hand.size() != 5){
			throw new UnsupportedOperationException("Player must have 5 cards to calculate set level.");
		}
		boolean straight = findStaight();
		boolean color = findColor();
		//high card
		if(pairs[0] + triplet[0] + quartet[0] == 0 && !straight && !color){
			handLevel = 0;
		}
		//1 pair
		if(pairs[0] == 1 && triplet[0] + quartet[0] == 0){
			handLevel = 1;
		}
		//2 pairs
		if(pairs[0] == 2 && triplet[0] + quartet[0] == 0){
			handLevel = 2;
		}
		//Three of a kind
		if(triplet[0] == 1 && pairs[0] + quartet[0] == 0){
			handLevel = 3;
		}
		//Straight
		if(straight && !color){
			handLevel = 4;
		}
		//Flush
		if(!straight && color){
			handLevel = 5;
		}
		//Full house
		if(pairs[0] == 1 && triplet[0] == 1){
			handLevel = 6;
		}
		//Four of a kind
		if(quartet[0] == 1){
			handLevel = 7;
		}
		//Straight flush
		if(straight && color){
			handLevel = 8;
		}
		//Royal flush
		if(straight && color && hand.get(hand.size() - 1).getFigure() == 14){
			handLevel = 9;
		}
	}
	/**
	 * Calculates power of a set/hand (used to settle draws when both players have same hand rank)
	 */
	public void calculateSetPower(){
		if(hand.size() != 5){
			throw new UnsupportedOperationException("Player must have 5 cards to calculate set power.");
		}
		handPower = Long.valueOf(0);
		int powerAmplifier = 1;
		Integer quantityAmplifier = 1;
		for(Map.Entry<Integer,Integer> entry : figuresQuantity.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if(value == 1){
				quantityAmplifier = 1;
			}
			if(value == 2){
				quantityAmplifier = 10000;
			}
			if(value == 3){
				quantityAmplifier = 1000000;
			}
			if(value == 4){
				quantityAmplifier = 100000000;
			}
			handPower += powerAmplifier * key *  quantityAmplifier;
			powerAmplifier *= 20;		
		}
	}
	/**
	 * Sorts cards by figures
	 */
	public void sortCards(){
		Collections.sort(hand, new CardsComparator());
	}
	/**
	 * Comparator of cards by figures.
	 * @author MARCIAUG
	 *
	 */
	class CardsComparator implements Comparator<Card>{

		@Override
		public int compare(Card c1, Card c2) {
			Integer card1 = c1.getFigure();
			Integer card2 = c2.getFigure();
			if(card1 > card2){
				return 1;
			}
			else if (card1 < card2){
				return -1;
			}
			return 0;
				
		}
	}
	
}
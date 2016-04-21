package augustyn.marcin.pokerfinal.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.pokerfinal.Card;
import augustyn.marcin.pokerfinal.CompareHands;
import augustyn.marcin.pokerfinal.Figures;
import augustyn.marcin.pokerfinal.Shapes;

public class CompareHandsTest {
	CompareHands comparator;
	List<Card> hand;
	@Before
	public void initialize(){
		comparator = new CompareHands();
		hand = new ArrayList<>();
	}
	@Test
	public void shouldReturnFiveForFindValueForColorWhenColor(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.THREE,Shapes.HEART));
		hand.add(new Card(Figures.SEVEN,Shapes.HEART));
		hand.add(new Card(Figures.FIVE,Shapes.HEART));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForColor(hand);
		//then 
			assertEquals(5, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForColorWhenNotColor(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.THREE,Shapes.CLUB));
		hand.add(new Card(Figures.SEVEN,Shapes.HEART));
		hand.add(new Card(Figures.FIVE,Shapes.HEART));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForColor(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnFourForFindValueForStraightWhenStraight(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.THREE,Shapes.CLUB));
		hand.add(new Card(Figures.FOUR,Shapes.HEART));
		hand.add(new Card(Figures.FIVE,Shapes.HEART));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(4, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForStraightWhenNotStraight(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.SEVEN,Shapes.CLUB));
		hand.add(new Card(Figures.FOUR,Shapes.HEART));
		hand.add(new Card(Figures.FIVE,Shapes.HEART));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForQuantitySetsWhenHighCard(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.SEVEN,Shapes.CLUB));
		hand.add(new Card(Figures.FOUR,Shapes.HEART));
		hand.add(new Card(Figures.FIVE,Shapes.HEART));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnOneForFindValueForQuantitySetsWhenPair(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.TWO,Shapes.CLUB));
		hand.add(new Card(Figures.FOUR,Shapes.DIAMOND));
		hand.add(new Card(Figures.FIVE,Shapes.SPADE));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(1, result);
	}
	@Test
	public void shouldReturnTwoForFindValueForQuantitySetsWhenTwoPairs(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.TWO,Shapes.CLUB));
		hand.add(new Card(Figures.FOUR,Shapes.DIAMOND));
		hand.add(new Card(Figures.FOUR,Shapes.SPADE));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(2, result);
	}
	@Test
	public void shouldReturnThreeForFindValueForQuantitySetsWhenThreeOfKind(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.TWO,Shapes.CLUB));
		hand.add(new Card(Figures.TWO,Shapes.DIAMOND));
		hand.add(new Card(Figures.FOUR,Shapes.SPADE));
		hand.add(new Card(Figures.SIX,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(3, result);
	}
	@Test
	public void shouldReturnSixForFindValueForQuantitySetsWhenFullHouse(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.TWO,Shapes.CLUB));
		hand.add(new Card(Figures.TWO,Shapes.DIAMOND));
		hand.add(new Card(Figures.FOUR,Shapes.SPADE));
		hand.add(new Card(Figures.FOUR,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(6, result);
	}
	@Test
	public void shouldReturnSevenForFindValueForQuantitySetsWhenFourOfKind(){
		//given
		hand.add(new Card(Figures.TWO,Shapes.HEART));
		hand.add(new Card(Figures.TWO,Shapes.CLUB));
		hand.add(new Card(Figures.TWO,Shapes.DIAMOND));
		hand.add(new Card(Figures.TWO,Shapes.SPADE));
		hand.add(new Card(Figures.FOUR,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(7, result);
	}
}

package augustyn.marcin.pokerfinal.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.pokerfinal.Card;
import augustyn.marcin.pokerfinal.CompareHands;
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
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(3,Shapes.HEART));
		hand.add(new Card(7,Shapes.HEART));
		hand.add(new Card(5,Shapes.HEART));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForColor(hand);
		//then 
			assertEquals(5, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForColorWhenNotColor(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(3,Shapes.CLUB));
		hand.add(new Card(7,Shapes.HEART));
		hand.add(new Card(5,Shapes.HEART));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForColor(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnFourForFindValueForStraightWhenStraight(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(3,Shapes.CLUB));
		hand.add(new Card(4,Shapes.HEART));
		hand.add(new Card(5,Shapes.HEART));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(4, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForStraightWhenNotStraight(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(7,Shapes.CLUB));
		hand.add(new Card(4,Shapes.HEART));
		hand.add(new Card(5,Shapes.HEART));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnZeroForFindValueForQuantitySetsWhenHighCard(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(7,Shapes.CLUB));
		hand.add(new Card(4,Shapes.HEART));
		hand.add(new Card(5,Shapes.HEART));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForStraight(hand);
		//then 
			assertEquals(0, result);
	}
	@Test
	public void shouldReturnOneForFindValueForQuantitySetsWhenPair(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(2,Shapes.CLUB));
		hand.add(new Card(4,Shapes.DIAMOND));
		hand.add(new Card(5,Shapes.SPADE));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(1, result);
	}
	@Test
	public void shouldReturnTwoForFindValueForQuantitySetsWhenTwoPairs(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(2,Shapes.CLUB));
		hand.add(new Card(4,Shapes.DIAMOND));
		hand.add(new Card(4,Shapes.SPADE));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(2, result);
	}
	@Test
	public void shouldReturnThreeForFindValueForQuantitySetsWhenThreeOfKind(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(2,Shapes.CLUB));
		hand.add(new Card(2,Shapes.DIAMOND));
		hand.add(new Card(4,Shapes.SPADE));
		hand.add(new Card(6,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(3, result);
	}
	@Test
	public void shouldReturnSixForFindValueForQuantitySetsWhenFullHouse(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(2,Shapes.CLUB));
		hand.add(new Card(2,Shapes.DIAMOND));
		hand.add(new Card(4,Shapes.SPADE));
		hand.add(new Card(4,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(6, result);
	}
	@Test
	public void shouldReturnSevenForFindValueForQuantitySetsWhenFourOfKind(){
		//given
		hand.add(new Card(2,Shapes.HEART));
		hand.add(new Card(2,Shapes.CLUB));
		hand.add(new Card(2,Shapes.DIAMOND));
		hand.add(new Card(2,Shapes.SPADE));
		hand.add(new Card(4,Shapes.HEART));
		
		//when
		int result = comparator.findValueForQuantitySets(hand);
		//then 
			assertEquals(7, result);
	}
}

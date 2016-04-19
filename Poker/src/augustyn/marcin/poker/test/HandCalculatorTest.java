package augustyn.marcin.poker.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.poker.HandCalculator;
import augustyn.marcin.poker.Main;


public class HandCalculatorTest {
	
	HandCalculator hand1;
	HandCalculator hand2;
	
	@Before
	public void initialize(){
		hand1 = new HandCalculator(1);
		hand2 = new HandCalculator(2);		
	}
	
	@Test
	public void shouldReturnFalseForStraightFinder(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(2, "D");
		hand1.pickCard(12, "H");
		hand1.pickCard(4, "C");
		hand1.pickCard(8, "D");
		hand1.sortCards();
		hand1.mapCards();
		hand1.findQuantitySet();
		//when
		boolean result = hand1.findStaight();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnTrueForStraightFinder(){
		//given
		hand1.pickCard(6, "C");
		hand1.pickCard(2, "D");
		hand1.pickCard(5, "H");
		hand1.pickCard(4, "C");
		hand1.pickCard(3, "D");
		hand1.sortCards();
		hand1.mapCards();
		hand1.findQuantitySet();
		//when
		boolean result = hand1.findStaight();
		//then 
		assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnFalseForColorFinder(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(2, "D");
		hand1.pickCard(12, "H");
		hand1.pickCard(4, "C");
		hand1.pickCard(8, "D");
		hand1.sortCards();
		hand1.mapCards();
		hand1.findQuantitySet();
		//when
		boolean result = hand1.findColor();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnTrueForColorFinder(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(2, "C");
		hand1.pickCard(12, "C");
		hand1.pickCard(4, "C");
		hand1.pickCard(8, "C");
		hand1.sortCards();
		hand1.mapCards();
		hand1.findQuantitySet();
		//when
		boolean result = hand1.findColor();
		//then 
		assertEquals(true, result);
	}
	@Test
	public void shouldReturnZeroForHighCard(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(2, "D");
		hand1.pickCard(12, "S");
		hand1.pickCard(4, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(0), result);
	}
	@Test
	public void shouldReturnOneForOnePair(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(10, "D");
		hand1.pickCard(12, "S");
		hand1.pickCard(4, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(1), result);
	}
	@Test
	public void shouldReturnTwoForTwoPairs(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(10, "D");
		hand1.pickCard(12, "S");
		hand1.pickCard(12, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(2), result);
	}
	
	@Test
	public void shouldReturnThreeForThreeOfKinds(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(10, "D");
		hand1.pickCard(10, "S");
		hand1.pickCard(4, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(3), result);
	}
	
	@Test
	public void shouldReturnFourForStraight(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(6, "D");
		hand1.pickCard(7, "S");
		hand1.pickCard(9, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(4), result);
	}
	@Test
	public void shouldReturnFiveForColor(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(2, "C");
		hand1.pickCard(12, "C");
		hand1.pickCard(4, "C");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(5), result);
	}
	
	@Test
	public void shouldReturnSixForFullHouse(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(10, "D");
		hand1.pickCard(10, "S");
		hand1.pickCard(4, "H");
		hand1.pickCard(4, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(6), result);
	}
	
	@Test
	public void shouldReturnSevenForFourOfKinds(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(10, "D");
		hand1.pickCard(10, "S");
		hand1.pickCard(10, "H");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(7), result);
	}
	
	@Test
	public void shouldReturnEightForStraightFlush(){
		//given
		hand1.pickCard(10, "C");
		hand1.pickCard(6, "C");
		hand1.pickCard(7, "C");
		hand1.pickCard(9, "C");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(8), result);
	}
	
	@Test
	public void shouldReturnNineForRoyalFlush(){
		//given
		hand1.pickCard(13, "C");
		hand1.pickCard(10, "C");
		hand1.pickCard(12, "C");
		hand1.pickCard(14, "C");
		hand1.pickCard(11, "C");
		hand1.calculateSetLevel();
		//when
		Integer result = hand1.getHandLevel();
		//then 
		assertEquals(Integer.valueOf(9), result);
	}
	
	@Test
	public void shouldReturnTwoInInWorstCasePairOfTwoVsPairOfThree(){
		//given
		hand1.pickCard(2, "C");
		hand1.pickCard(2, "H");
		hand1.pickCard(14, "S");
		hand1.pickCard(13, "D");
		hand1.pickCard(12, "C");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(3, "C");
		hand2.pickCard(3, "H");
		hand2.pickCard(2, "S");
		hand2.pickCard(4, "D");
		hand2.pickCard(5, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		//when
		int result = Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		//then 
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnTwoInWorstCaseTripleOfTwoVsTripleOfThree(){
		//given
		hand1.pickCard(2, "C");
		hand1.pickCard(2, "H");
		hand1.pickCard(14, "S");
		hand1.pickCard(13, "D");
		hand1.pickCard(2, "D");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(3, "C");
		hand2.pickCard(3, "H");
		hand2.pickCard(2, "S");
		hand2.pickCard(4, "D");
		hand2.pickCard(3, "D");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		//when
		int result = Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		//then 
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnOneInWorstCaseTwoPairsVsTwoPairs(){
		//given
		hand1.pickCard(2, "C");
		hand1.pickCard(2, "H");
		hand1.pickCard(14, "S");
		hand1.pickCard(14, "D");
		hand1.pickCard(3, "C");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(13, "C");
		hand2.pickCard(13, "H");
		hand2.pickCard(12, "S");
		hand2.pickCard(12, "D");
		hand2.pickCard(11, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		//when
		int result = Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		//then 
		assertEquals(1, result);
	}
	@Test
	public void shouldReturnOneInWorstCaseThreeOfKindVsTwoPairs(){
		//given
		hand1.pickCard(2, "C");
		hand1.pickCard(2, "H");
		hand1.pickCard(2, "S");
		hand1.pickCard(3, "D");
		hand1.pickCard(4, "C");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(13, "C");
		hand2.pickCard(13, "H");
		hand2.pickCard(12, "S");
		hand2.pickCard(12, "D");
		hand2.pickCard(11, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		//when
		int result = Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		//then 
		assertEquals(1, result);
	}
	
	@Test
	public void shouldReturnTwoInWorstCaseHighCardVsHighCard(){
		//given
		hand1.pickCard(13, "C");
		hand1.pickCard(12, "H");
		hand1.pickCard(11, "S");
		hand1.pickCard(10, "D");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(14, "C");
		hand2.pickCard(2, "H");
		hand2.pickCard(3, "S");
		hand2.pickCard(4, "D");
		hand2.pickCard(6, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		//when
		int result = Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		//then 
		assertEquals(2, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenFigureIsOutOfUpperBoundInPickCard(){
		//given
				
		//when
		
		hand1.pickCard(15, "C");
		
		//then 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenFigureIsOutOfLowerBoundInPickCard(){
		//given
				
		//when
		
		hand1.pickCard(1, "C");
		
		//then 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenColorIsInvalidInPickCard(){
		//given
				
		//when
		
		hand1.pickCard(5, "A");
		
		//then 
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryToPickMoreThenFiveCards(){
		//given
				
		//when
		
		hand1.pickCard(2, "D");
		hand1.pickCard(3, "D");
		hand1.pickCard(4, "D");
		hand1.pickCard(5, "D");
		hand1.pickCard(6, "D");
		hand1.pickCard(7, "D");
		
		//then 
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryToCalculateSetLevelAndPlayerHasLessThenFiveCards(){
		//given
				
		//when
		
		hand1.pickCard(2, "D");
		hand1.pickCard(3, "D");
		hand1.pickCard(4, "D");
		hand1.pickCard(5, "D");
		hand1.calculateSetLevel();
		
		//then 
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryToCalculateSetPowerAndPlayerHasLessThenFiveCards(){
		//given
				
		//when
		
		hand1.pickCard(2, "D");
		hand1.pickCard(3, "D");
		hand1.pickCard(4, "D");
		hand1.pickCard(5, "D");
		hand1.calculateSetPower();
		
		//then 
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryToCompareHandsAndCalculateSetLevelWasNotExecuted(){
		//given
				
		//when
		
		hand1.pickCard(13, "C");
		hand1.pickCard(12, "H");
		hand1.pickCard(11, "S");
		hand1.pickCard(10, "D");
		hand1.pickCard(8, "C");
		
		Integer handLevel1 = hand1.getHandLevel();
		hand1.calculateSetPower();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(14, "C");
		hand2.pickCard(2, "H");
		hand2.pickCard(3, "S");
		hand2.pickCard(4, "D");
		hand2.pickCard(6, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		
		//then 
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenTryToCompareHandsAndCalculateSetPowerWasNotExecuted(){
		//given
				
		//when
		
		hand1.pickCard(13, "C");
		hand1.pickCard(12, "H");
		hand1.pickCard(11, "S");
		hand1.pickCard(10, "D");
		hand1.pickCard(8, "C");
		hand1.calculateSetLevel();
		Integer handLevel1 = hand1.getHandLevel();
		Long setPower1 = hand1.getHandPower();

		hand2.pickCard(14, "C");
		hand2.pickCard(2, "H");
		hand2.pickCard(3, "S");
		hand2.pickCard(4, "D");
		hand2.pickCard(6, "C");
		hand2.calculateSetLevel();
		Integer handLevel2 = hand2.getHandLevel();
		hand2.calculateSetPower();
		Long setPower2 = hand2.getHandPower();
		
		Main.compareHands(handLevel1, setPower1, handLevel2, setPower2);
		
		//then 
	}
}

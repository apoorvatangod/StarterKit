package augustyn.marcin.bowling.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.bowling.ScoreCalculator;

public class ScoreCalculatorTest {
	
	ScoreCalculator player1;
	
	@Before
	public void initialize(){
		player1 = new ScoreCalculator(1);
		
	}
	@Test
	public void shouldStartWithZeroScore(){
		//given
		
		//when
		int score = player1.score();
		//then 
		assertEquals(0, score);
	}
	
	@Test
	public void shouldStartWithFlaseForIsFinished(){
		//given

		//when
		boolean isFinishedAtStart = player1.isFinished();
		//then 
		assertEquals(false, isFinishedAtStart);
	}
	
	@Test
	public void shouldReturnTenForFirstRollBeingTen(){
		//given
		player1.roll(10);
		//when
		int score = player1.score();
		//then 
		assertEquals(10, score);
	}
	
	@Test
	public void shouldReturnZeroForFirstRollBeingZero(){
		//given
		player1.roll(0);
		//when
		int score = player1.score();
		//then 
		assertEquals(0, score);
	}
	
	@Test
	public void shouldReturnTwelveWhenRollTenOne(){
		//given
		player1.roll(10);
		player1.roll(1);
		//when
		int score = player1.score();
		//then 
		assertEquals(12, score);
	}
	
	@Test
	public void shouldReturnSixteenWhenRollTwoEightThree(){
		//given
		player1.roll(2);
		player1.roll(8);
		player1.roll(3);
		//when
		int score = player1.score();
		//then 
		assertEquals(16, score);
	}
	
	@Test
	public void shouldReturnThirtyWhenRollTenTen(){
		//given
		player1.roll(10);
		player1.roll(10);
		//when
		int score = player1.score();
		//then 
		assertEquals(30, score);
	}
	
	@Test
	public void shouldReturnThirtyThreeWhenRollTenTenOne(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(1);
		//when
		int score = player1.score();
		//then 
		assertEquals(33, score);
	}
	
	@Test
	public void shouldReturnElevenWhenRollFiveZeroSix(){
		//given
		player1.roll(5);
		player1.roll(0);
		player1.roll(6);
		//when
		int score = player1.score();
		//then 
		assertEquals(11, score);
	}
	
	@Test
	public void shouldReturnTenlveWhenRollOneNine(){
		//given
		player1.roll(1);
		player1.roll(9);
		//when
		int score = player1.score();
		//then 
		assertEquals(10, score);
	}
	@Test
	public void shouldReturnThreeHounderedWhenRollAllTens(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		//when
		int score = player1.score();
		//then 
		assertEquals(300, score);
	}
	
	@Test
	public void shouldReturn271WhenRollAllTenButOneNineTenInLastRound(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(1);
		player1.roll(9);
		player1.roll(10);
		//when
		int score = player1.score();
		//then 
		assertEquals(271, score);
	}
	
	@Test
	public void shouldReturn134WhenRollRandomGameWithoutBonusRound(){
		//given
		player1.roll(3);
		player1.roll(6);
		player1.roll(10);
		player1.roll(2);
		player1.roll(8);
		player1.roll(3);
		player1.roll(6);
		player1.roll(10);
		player1.roll(0);
		player1.roll(10);
		player1.roll(9);
		player1.roll(1);
		player1.roll(3);
		player1.roll(6);
		player1.roll(10);
		player1.roll(1);
		player1.roll(5);
		//when
		int score = player1.score();

		//then 
		assertEquals(134, score);
		
	}
	
	@Test
	public void shouldReturn187WhenUsingExampleGameFromPresentation(){
		//given
		player1.roll(10);
		player1.roll(9);
		player1.roll(1);
		player1.roll(5);
		player1.roll(5);
		player1.roll(7);
		player1.roll(2);
		player1.roll(10);
		player1.roll(10);;
		player1.roll(10);
		player1.roll(9);
		player1.roll(0);
		player1.roll(8);
		player1.roll(2);
		player1.roll(9);
		player1.roll(1);
		player1.roll(10);
		//when
		int score = player1.score();
		//then 
		assertEquals(187, score);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRollFiveSixInOneRound(){
		//given

		//when
		player1.roll(5);
		player1.roll(6);
		
		//then 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRollIsMinusOne(){
		//given

		//when
		player1.roll(-1);
		
		//then 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenRollIsEleven(){
		//given

		//when
		player1.roll(11);
		
		//then 
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenRollAllTens(){
		//given
		for (int i = 0; i < 12; i++){
			player1.roll(10);
		}
		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenInTheMiddleOfGame(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);

		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenNoBonusRound(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(1);
		player1.roll(1);

		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenStrikeInSecondRollInLastRound(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(5);
		player1.roll(5);
		player1.roll(1);

		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(true, result);
	}
	@Test
	public void shouldReturnIsFinishedFalseWhenLastRoundIsOneNine(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(1);
		player1.roll(9);

		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenLastRoundIsOneNineTen(){
		//given
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(10);
		player1.roll(1);
		player1.roll(9);
		player1.roll(10);

		//when
		boolean result = player1.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenRollAfterLastRound(){
		//given

		//when
		for (int i = 0; i < 13; i++){
			player1.roll(10);
		}
		
		//then 
	}
}

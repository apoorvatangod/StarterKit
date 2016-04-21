package augustyn.marcin.bowlingfinal.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.bowlingfinal.BowlingException;
import augustyn.marcin.bowlingfinal.Game;

public class GameTest {
	
	Game game;
	
	@Before
	public void initialize(){
		game = new Game();
		
	}
	@Test
	public void shouldStartWithZeroScore(){
		//given
		
		//when
		int score = game.score();
		//then 
		assertEquals(0, score);
	}
	
	@Test
	public void shouldStartWithFlaseForIsFinished(){
		//given

		//when
		boolean isFinishedAtStart = game.isFinished();
		//then 
		assertEquals(false, isFinishedAtStart);
	}
	
	@Test
	public void shouldReturnTenForFirstRollBeingTen() throws BowlingException{
		//given
		game.roll(10);
		//when
		int score = game.score();
		//then 
		assertEquals(10, score);
	}
	
	@Test
	public void shouldReturnZeroForFirstRollBeingZero() throws BowlingException{
		//given
		game.roll(0);
		//when
		int score = game.score();
		//then 
		assertEquals(0, score);
	}
	
	@Test
	public void shouldReturnTwelveWhenRollTenOne() throws BowlingException{
		//given
		game.roll(10);
		game.roll(1);
		//when
		int score = game.score();
		//then 
		assertEquals(12, score);
	}
	
	@Test
	public void shouldReturnSixteenWhenRollTwoEightThree() throws BowlingException{
		//given
		game.roll(2);
		game.roll(8);
		game.roll(3);
		//when
		int score = game.score();
		//then 
		assertEquals(16, score);
	}
	
	@Test
	public void shouldReturnThirtyWhenRollTenTen() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		//when
		int score = game.score();
		//then 
		assertEquals(30, score);
	}
	
	@Test
	public void shouldReturnThirtyThreeWhenRollTenTenOne() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(1);
		//when
		int score = game.score();
		//then 
		assertEquals(33, score);
	}
	
	@Test
	public void shouldReturnElevenWhenRollFiveZeroSix() throws BowlingException{
		//given
		game.roll(5);
		game.roll(0);
		game.roll(6);
		//when
		int score = game.score();
		//then 
		assertEquals(11, score);
	}
	
	@Test
	public void shouldReturnTenlveWhenRollOneNine() throws BowlingException{
		//given
		game.roll(1);
		game.roll(9);
		//when
		int score = game.score();
		//then 
		assertEquals(10, score);
	}
	
	@Test
	public void shouldReturnThreeHounderedWhenRollAllTens() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		//when
		int score = game.score();
		//then 
		assertEquals(300, score);
	}
	
	@Test
	public void shouldReturn271WhenRollAllTenButOneNineTenInLastRound() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(1);
		game.roll(9);
		game.roll(10);
		//when
		int score = game.score();
		//then 
		assertEquals(271, score);
	}
	
	@Test
	public void shouldReturn134WhenRollRandomGameWithoutBonusRound() throws BowlingException{
		//given
		game.roll(3);
		game.roll(6);
		game.roll(10);
		game.roll(2);
		game.roll(8);
		game.roll(3);
		game.roll(6);
		game.roll(10);
		game.roll(0);
		game.roll(10);
		game.roll(9);
		game.roll(1);
		game.roll(3);
		game.roll(6);
		game.roll(10);
		game.roll(1);
		game.roll(5);
		//when
		int score = game.score();

		//then 
		assertEquals(134, score);
		
	}
	
	@Test
	public void shouldReturn187WhenUsingExampleGameFromPresentation() throws BowlingException{
		//given
		game.roll(10);
		game.roll(9);
		game.roll(1);
		game.roll(5);
		game.roll(5);
		game.roll(7);
		game.roll(2);
		game.roll(10);
		game.roll(10);;
		game.roll(10);
		game.roll(9);
		game.roll(0);
		game.roll(8);
		game.roll(2);
		game.roll(9);
		game.roll(1);
		game.roll(10);
		//when
		int score = game.score();
		//then 
		assertEquals(187, score);
	}
	
	@Test(expected = BowlingException.class)
	public void shouldThrowExceptionWhenRollFiveSixInOneRound() throws BowlingException{
		//given

		//when
		game.roll(5);
		game.roll(6);
		
		//then 
	}
	
	@Test(expected = BowlingException.class)
	public void shouldThrowExceptionWhenRollIsMinusOne() throws BowlingException{
		//given

		//when
		game.roll(-1);
		
		//then 
	}
	
	@Test(expected = BowlingException.class)
	public void shouldThrowExceptionWhenRollIsEleven() throws BowlingException{
		//given

		//when
		game.roll(11);
		
		//then 
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenRollAllTens() throws BowlingException{
		//given
		for (int i = 0; i < 12; i++){
			game.roll(10);
		}
		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenInTheMiddleOfGame() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);

		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenNoBonusRound() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(1);
		game.roll(1);

		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenStrikeInSecondRollInLastRound() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(5);
		game.roll(5);
		game.roll(1);

		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(true, result);
	}
	@Test
	public void shouldReturnIsFinishedFalseWhenLastRoundIsOneNine() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(1);
		game.roll(9);

		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(false, result);
	}
	
	@Test
	public void shouldReturnIsFinishedTrueWhenLastRoundIsOneNineTen() throws BowlingException{
		//given
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(1);
		game.roll(9);
		game.roll(10);

		//when
		boolean result = game.isFinished();
		//then 
		assertEquals(true, result);
	}
	
	@Test(expected = BowlingException.class)
	public void shouldThrowExceptionWhenRollAfterLastRound() throws BowlingException{
		//given

		//when
		for (int i = 0; i < 13; i++){
			game.roll(10);
		}
		
		//then 
	}
}

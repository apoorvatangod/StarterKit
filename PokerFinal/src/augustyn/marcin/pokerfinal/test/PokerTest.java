package augustyn.marcin.pokerfinal.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import augustyn.marcin.pokerfinal.Poker;
import augustyn.marcin.pokerfinal.PokerException;

public class PokerTest {
	
	@Test
	public void shouldReturn381ForAllGames() throws PokerException{
		//given
		Poker poker = new Poker();
		//when
		int result = poker.playAllGames();
		//then 
		assertEquals(376, result);
	}

}

package augustyn.marcin.pokerfinal.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import augustyn.marcin.pokerfinal.Poker;

public class PokerTest {
	
	@Test
	public void shouldReturn381ForAllGames(){
		//given
		Poker poker = new Poker();
		//when
		int result = poker.playAllGames();
		//then 
		assertEquals(376, result);
	}

}

package augustyn.marcin.pokerfinal.test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.pokerfinal.Card;
import augustyn.marcin.pokerfinal.Converter;
import augustyn.marcin.pokerfinal.Figures;
import augustyn.marcin.pokerfinal.PokerException;
import augustyn.marcin.pokerfinal.Shapes;

public class ConverterTest {
	Converter converter;
	List<Card> hand;
	@Before
	public void initialize(){
		converter = new Converter();	
		hand = new ArrayList<>();
	}
	@Test
	public void shouldParseFiguresCorrectlyForFirstHand() throws PokerException{
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(Figures.EIGHT,Shapes.CLUB));
		expectedHand.add(new Card(Figures.TEN,Shapes.SPADE));
		expectedHand.add(new Card(Figures.KING,Shapes.CLUB));
		expectedHand.add(new Card(Figures.NINE,Shapes.HEART));
		expectedHand.add(new Card(Figures.FOUR,Shapes.SPADE));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 0; i < 5; i++){
			assertEquals(expectedHand.get(i).getFigure(), hand.get(i).getFigure());
		}
	}
	@Test
	public void shouldParseFiguresCorrectlyForSecondHand() throws PokerException{
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(Figures.SEVEN,Shapes.DIAMOND));
		expectedHand.add(new Card(Figures.TWO,Shapes.SPADE));
		expectedHand.add(new Card(Figures.FIVE,Shapes.DIAMOND));
		expectedHand.add(new Card(Figures.THREE,Shapes.SPADE));
		expectedHand.add(new Card(Figures.ACE,Shapes.CLUB));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 5; i < 10; i++){
			assertEquals(expectedHand.get(i - 5).getFigure(), hand.get(i).getFigure());
		}
	}
	@Test
	public void shouldParseFiguresCorrectlyForFirstHandSecondGame() throws PokerException{
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(Figures.FIVE,Shapes.CLUB));
		expectedHand.add(new Card(Figures.ACE,Shapes.DIAMOND));
		expectedHand.add(new Card(Figures.FIVE,Shapes.DIAMOND));
		expectedHand.add(new Card(Figures.ACE,Shapes.CLUB));
		expectedHand.add(new Card(Figures.NINE,Shapes.CLUB));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 10; i < 15; i++){
			assertEquals(expectedHand.get(i - 10).getFigure(), hand.get(i).getFigure());
		}
	}
}

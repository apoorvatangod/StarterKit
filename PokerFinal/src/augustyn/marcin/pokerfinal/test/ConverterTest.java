package augustyn.marcin.pokerfinal.test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import augustyn.marcin.pokerfinal.Card;
import augustyn.marcin.pokerfinal.Converter;
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
	public void shouldParseFiguresCorrectlyForFirstHand(){
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(8,Shapes.CLUB));
		expectedHand.add(new Card(10,Shapes.SPADE));
		expectedHand.add(new Card(13,Shapes.CLUB));
		expectedHand.add(new Card(9,Shapes.HEART));
		expectedHand.add(new Card(4,Shapes.SPADE));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 0; i < 5; i++){
			assertEquals(expectedHand.get(i).getFigure(), hand.get(i).getFigure());
		}
	}
	@Test
	public void shouldParseFiguresCorrectlyForSecondHand(){
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(7,Shapes.DIAMOND));
		expectedHand.add(new Card(2,Shapes.SPADE));
		expectedHand.add(new Card(5,Shapes.DIAMOND));
		expectedHand.add(new Card(3,Shapes.SPADE));
		expectedHand.add(new Card(14,Shapes.CLUB));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 5; i < 10; i++){
			assertEquals(expectedHand.get(i - 5).getFigure(), hand.get(i).getFigure());
		}
	}
	@Test
	public void shouldParseFiguresCorrectlyForFirstHandSecondGame(){
		//given
		List<Card> expectedHand = new ArrayList<>();
		expectedHand.add(new Card(5,Shapes.CLUB));
		expectedHand.add(new Card(14,Shapes.DIAMOND));
		expectedHand.add(new Card(5,Shapes.DIAMOND));
		expectedHand.add(new Card(14,Shapes.CLUB));
		expectedHand.add(new Card(9,Shapes.CLUB));
		//when
		hand = converter.convertFromFile();
		//then 
		for (int i = 10; i < 15; i++){
			assertEquals(expectedHand.get(i - 10).getFigure(), hand.get(i).getFigure());
		}
	}
}

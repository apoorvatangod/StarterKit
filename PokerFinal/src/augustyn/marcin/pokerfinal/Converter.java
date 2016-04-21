package augustyn.marcin.pokerfinal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Converter {
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(Converter.class);
	private static final int CARDS_PER_GAME = 10;
	/**
	 * Converts text file to card objects.
	 * @return list of card objects.
	 * @throws PokerException 
	 */
	public List<Card> convertFromFile() throws PokerException{
		List<Card> cardsRead = new ArrayList<>();
		List<String> readLines = readLinesFromFile();
		for(String line : readLines){
			List<String> splittedCards = splitLinesIntoCards(line);
			List<Card> cardsFromLine = transformCardsIntoObjects(splittedCards);
			cardsRead.addAll(cardsFromLine);
		}
		return cardsRead;
	}
	/**
	 * Gives number of games read from file.
	 * @return number of games
	 */
	public int getNumberOfGames(List<Card> allCards){
		return allCards.size() / CARDS_PER_GAME;
	}
	private List<String> readLinesFromFile() throws PokerException{
		Reader reader = new Reader();
		return reader.readLinesFromFile();
	}
	
	private List<String> splitLinesIntoCards(String line) throws PokerException{
		List<String> splitedCards = new ArrayList<>();
		String[] splitedLines= StringUtils.split(line);
		if(splitedLines.length != CARDS_PER_GAME){
			throw new PokerException("Number of cards in one line is different than 10.");
		}
		for(String card : splitedLines){
			splitedCards.add(card);
		}
		return splitedCards;
	}
	
	private List<Card> transformCardsIntoObjects(List<String> splitedCards) throws PokerException{
		List<Card> cardsRead = new ArrayList<>();
		for(String card : splitedCards){
			if(card.length() != 2){
				throw new PokerException("Card has to consist of 2 chars."); 
			}
			Figures figure = Figures.getEnumBySymbol(card.substring(0, 1));
			Shapes shape = Shapes.getEnumBySymbol(card.substring(1, 2));
			Card newCard = new Card(figure,shape);
			cardsRead.add(newCard);
		}
		return cardsRead;
	}
}

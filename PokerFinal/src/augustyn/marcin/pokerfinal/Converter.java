package augustyn.marcin.pokerfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Converter {
	private static final Logger logger = LogManager.getLogger(Converter.class);
	private static final String FILE_PATH = "poker.txt";
	private static final int CARDS_PER_GAME = 10;
	private List<Card> cardsRead = new ArrayList<>();
	private List<String> splitedCards = new ArrayList<>();
	
	
	private void readCardsFromFile(){
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_PATH)));
			
			String line;
			while((line = bufferedReader.readLine()) != null){
				splitLinesIntoCards(line);
			}
			
			try {
				bufferedReader.close();
			} catch (IOException e) {
				logger.error("Cannot close file: " + FILE_PATH);
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found: " + FILE_PATH);
		} catch (IOException e) {
			logger.error("Unable to read file: " + FILE_PATH);
		}
	}
	
	private List<String> splitLinesIntoCards(String line){
		String[] splitedLines= StringUtils.split(line);
		for(String card : splitedLines){
			splitedCards.add(card);
		}
		if(splitedLines.length != CARDS_PER_GAME){
			throw new IllegalArgumentException("Number of cards in one line is different than 10.");
		}
		return splitedCards;
	}
	
	private void transformCardsIntoObjects(){
		for(String card : splitedCards){
			int figure = Figures.getValueBySymbol(card.substring(0, 1));
			Shapes shape = Shapes.getValueBySymbol(card.substring(1, 2));
			Card newCard = new Card(figure,shape);
			cardsRead.add(newCard);
		}
	}
	public List<Card> convertFromFile(){
		readCardsFromFile();
		transformCardsIntoObjects();
		return cardsRead;
	}
	public int getNumberOfGames(){
		return cardsRead.size() / CARDS_PER_GAME;
	}
}

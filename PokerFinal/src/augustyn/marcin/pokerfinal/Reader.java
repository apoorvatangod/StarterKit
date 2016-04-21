package augustyn.marcin.pokerfinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reader {
	private static final Logger logger = LogManager.getLogger(Converter.class);
	private static final String FILE_PATH = "poker.txt";
	
	public List<String> readLinesFromFile() throws PokerException{
		List<String> readLines = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_PATH)));
			
			String line;
			while((line = bufferedReader.readLine()) != null){
				readLines.add(line);
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
		return readLines;
	}
}

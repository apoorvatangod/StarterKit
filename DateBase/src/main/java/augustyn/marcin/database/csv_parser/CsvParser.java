package augustyn.marcin.database.csv_parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvParser {
	private static final Logger logger = LogManager.getLogger(CsvParser.class);
	
	public List<List<String>> parseCsvFile(File file){
		List<List<String>> readLines = new ArrayList<>();
		try {
			final CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT.withHeader());
			
			readLines.add(new ArrayList<String>());
			for(String headerColumnName : parser.getHeaderMap().keySet()){	
				readLines.get(0).add(headerColumnName);
			}
			
			for (CSVRecord row : parser) {
				readLines.add(new ArrayList<String>());
				for(int i = 1; i <= parser.getHeaderMap().size(); i++){
					readLines.get(readLines.size() - 1).add(row.get(i - 1));
				}
		    }
			parser.close();

		} catch (IOException e) {
			logger.info(e);
		}
		return readLines;
	}
}

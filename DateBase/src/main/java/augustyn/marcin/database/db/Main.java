package augustyn.marcin.database.db;


import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import augustyn.marcin.database.csv_parser.CsvParser;
import augustyn.marcin.database.repository.ForumRepository;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static PrintService service = new PrintService();
	private static ForumRepository repo = new ForumRepository();
	private static CsvParser csvParser = new CsvParser();
	
	public static void main(String[] args){
		File file = new File("C:/Users/marciaug/Desktop/input-MYTAB.csv");
		service.printTable("categories", 2, -1);
		
		List<List<String>> result = csvParser.parseCsvFile(file);
		//repo.insertDataSetToTable("categories", result);
		/*logger.info(repo.getColumnDataTypeByColumnName("categories", "id"));
		logger.info(repo.getColumnDataTypeByColumnName("categories", "cat_name"));
		logger.info(repo.getColumnDataTypeByColumnName("categories", "disp_position"));*/
		/*for(List<String> row : result){
			for(String record : row){
				logger.info(record);
			}
		}*/
	}
}

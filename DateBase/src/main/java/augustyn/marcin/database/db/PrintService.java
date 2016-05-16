package augustyn.marcin.database.db;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import augustyn.marcin.database.repository.ForumRepository;


public class PrintService {
	private ForumRepository repository = new ForumRepository();
	
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public void printTable(String tableName, int fromRowIdx, int toRowIdx){
		
		 if(fromRowIdx < 1){ 
			 fromRowIdx = 1;
		 }
		 
		 printHeader(repository.getDbHeaderRow(tableName), tableName);
		 printRows(repository.executeDbQuery(tableName, fromRowIdx - 1, (toRowIdx - fromRowIdx) + 1));	
	}
	
	private void printLine(int lenght){
		logger.info(new String(new char[lenght]).replace('\0', '-'));
	}
	private void printRows(List<String> rowsWithData){
		printLine(rowsWithData.get(0).length());
		rowsWithData.forEach(line -> logger.info(line));
		printLine(rowsWithData.get(0).length());
	}
	private void printHeader(String header, String tableName){
		printLine(header.length());
		logger.info("\tTABLE: " + tableName);
		printLine(header.length());
		logger.info(header);
	}
}

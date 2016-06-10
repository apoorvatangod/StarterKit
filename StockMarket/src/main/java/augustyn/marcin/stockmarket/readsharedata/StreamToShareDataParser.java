package augustyn.marcin.stockmarket.readsharedata;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import augustyn.marcin.stockmarket.stock.to.ShareDataTo;

public class StreamToShareDataParser {
	//TODO separator, filePath i dateformat do properties - czy bedzie sie dalo zaczytac jesli wywolujemy w postconstruct???
	private static final Logger logger = LogManager.getLogger(StreamToShareDataParser.class);
	private final static String SEPARATOR = ",";
	private final static int MINIMAL_INT_TO_BE_TREATED_AS_DATE = 19000000;
	private final static Format formatter = new SimpleDateFormat("yyyyMMdd");
	
	public static ShareDataTo parse(String line){
		String[] elements = line.split(SEPARATOR);
		ShareDataTo shareData = new ShareDataTo();
		for(String element : elements){
			if(isDouble(element) && Double.parseDouble(element) < MINIMAL_INT_TO_BE_TREATED_AS_DATE){
				Double priceAsDouble = Double.parseDouble(element);
				shareData.setPrice((int) (priceAsDouble * 100));
			} 
			if(isInteger(element) && Integer.parseInt(element) > MINIMAL_INT_TO_BE_TREATED_AS_DATE){
				try {
					shareData.setDate((Date)((DateFormat) formatter).parse(element));
				} catch (ParseException e) {
					logger.error("Error during parsin Integer: " + element);
				}
			}
			if(!isDouble(element) && !isInteger(element)){
				shareData.setName(element);
			}
		}
	return (shareData.getDate() != null && shareData.getName() != null && shareData.getPrice() > 0) ? shareData : null;
	}
	
	private static boolean isDouble(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private static boolean isInteger(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

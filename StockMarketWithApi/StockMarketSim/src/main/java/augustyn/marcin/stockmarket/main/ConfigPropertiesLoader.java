package augustyn.marcin.stockmarket.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ConfigPropertiesLoader {
	
	public List<DateTime> getPropopertiesValues() throws IOException {
		List<DateTime> result = new ArrayList<>();
		InputStream inputStream = null;
		try {
			Properties properties = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath.");
			}
			DateTimeFormatter format = DateTimeFormat.forPattern(properties.getProperty("simBoundsDateFormat"));
			
			String startDate = properties.getProperty("startDate");
			String endDate = properties.getProperty("endDate");
			
			result.add(format.parseDateTime(startDate));
			result.add(format.parseDateTime(endDate));
			
		} catch (Exception e) {
			throw new IOException("Error during reading properties.");
		} finally {
			inputStream.close();
		}
		return result;
	}
}

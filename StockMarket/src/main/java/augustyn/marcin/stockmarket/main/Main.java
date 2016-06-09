package augustyn.marcin.stockmarket.main;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import augustyn.marcin.stockmarket.calendar.MyCalendar;
import augustyn.marcin.stockmarket.player.Player;

@Component
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	@Autowired
	private Player player;
	
	@Autowired
	private MyCalendar calendar;

	public void executeSim() {
		List<DateTime> properties = loadConfig();
		calendar.setStartDay(properties.get(0));
		do{
			player.performActions();
			calendar.changeToNextDay();
			
		}while(!calendar.getCurrentDate().equals(properties.get(1)));
	}
	
	private List<DateTime> loadConfig(){
		ConfigPropertiesLoader loader = new ConfigPropertiesLoader();
		try {
			return loader.getPropopertiesValues();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return null;
	}
}

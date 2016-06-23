package augustyn.marcin.stockmarket.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import augustyn.marcin.stockmarket.broker.to.OfferTo;
import augustyn.marcin.stockmarket.calendar.MyCalendar;
import augustyn.marcin.stockmarket.player.Player;

@Service
public class MainImpl implements Main{
	private static final Logger logger = LogManager.getLogger(MainImpl.class);
	
	@Autowired
	private Player player;
	
	@Autowired
	private MyCalendar calendar;
	
	@Autowired
	ConfigPropertiesLoader loader;

	@Override
	public List<OfferTo> executeSim() {
		List<DateTime> properties = loadConfig();
		calendar.setStartDay(properties.get(0));
		List<OfferTo> allExecutedOffers = new ArrayList<>();
		if(properties != null){
			do{
				List<OfferTo> executedOffersInCurrentDay = player.performActions();
				if(!executedOffersInCurrentDay.isEmpty()){
					allExecutedOffers = Stream
							.concat(allExecutedOffers.stream(), executedOffersInCurrentDay.stream())
							.collect(Collectors.toList());
				}
				calendar.changeToNextDay();
				
			}while(!calendar.getCurrentDate().equals(properties.get(1)));
		}
		return allExecutedOffers;
	}
	
	private List<DateTime> loadConfig(){
		try {
			return loader.getPropopertiesValues();
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}

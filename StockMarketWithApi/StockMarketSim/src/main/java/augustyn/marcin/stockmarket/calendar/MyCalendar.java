package augustyn.marcin.stockmarket.calendar;

import org.joda.time.DateTime;

public interface MyCalendar {
	
	public DateTime getCurrentDate();
	
	public void changeToNextDay();
	
	public void setStartDay(DateTime date);
}

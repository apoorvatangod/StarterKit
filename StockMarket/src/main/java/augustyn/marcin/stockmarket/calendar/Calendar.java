package augustyn.marcin.stockmarket.calendar;

import java.util.Date;

public interface Calendar {
	
	public Date getCurrentDate();
	
	public void changeToNextDay();
	
	public void setStartDay(Date date);
}

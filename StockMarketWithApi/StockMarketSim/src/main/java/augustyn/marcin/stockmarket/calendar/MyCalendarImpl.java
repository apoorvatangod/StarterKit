package augustyn.marcin.stockmarket.calendar;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Service
public class MyCalendarImpl implements MyCalendar {
	
	private Calendar calendar = Calendar.getInstance();

	@Override
	public DateTime getCurrentDate() {
		return new DateTime(calendar.getTime());
	}

	@Override
	public void changeToNextDay() {
		calendar.setTime(getCurrentDate().plusDays(1).toDate());

	}

	@Override
	public void setStartDay(DateTime date) {
		calendar.setTime(date.toDate());
	}

}

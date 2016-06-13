package augustyn.marcin.stockmarket.calendar.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import augustyn.marcin.stockmarket.calendar.MyCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MyCalendarImplTest {
	
	@Autowired
	private MyCalendar calendar;

	@Test
	public void testSetStartDate() {
		// given
		DateTime startDate = new DateTime(new Date(946684800000L));
		
		// when
		calendar.setStartDay(startDate);
		
		DateTime result = calendar.getCurrentDate();

		// then

		assertEquals(startDate, result);
	}
	
	@Test
	public void testNextDay() {
		// given
		DateTime startDate = new DateTime(new Date(946684800000L));
		DateTime nextDay = startDate.plusDays(1);
		// when
		calendar.setStartDay(startDate);
		calendar.changeToNextDay();
		
		DateTime result = calendar.getCurrentDate();

		// then

		assertEquals(nextDay, result);
	}
}

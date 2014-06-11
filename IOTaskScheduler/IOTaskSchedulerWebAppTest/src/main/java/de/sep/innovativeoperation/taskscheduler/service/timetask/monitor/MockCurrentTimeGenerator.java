package de.sep.innovativeoperation.taskscheduler.service.timetask.monitor;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class MockCurrentTimeGenerator implements CurrentTimeGenerator{

	
	public Calendar getInstance(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(1388534400000L);
		
		return calendar;
	}
}

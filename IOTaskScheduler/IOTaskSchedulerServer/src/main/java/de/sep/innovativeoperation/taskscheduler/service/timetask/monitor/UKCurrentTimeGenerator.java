package de.sep.innovativeoperation.taskscheduler.service.timetask.monitor;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.stereotype.Service;


@Service
public class UKCurrentTimeGenerator implements CurrentTimeGenerator {
	private Locale locale = Locale.UK;
	
	public Calendar getInstance(){
		return Calendar.getInstance(locale);
	}
}

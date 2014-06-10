package de.sep.innovativeoperation.taskscheduler.service.timetask.monitor;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public interface CurrentTimeGenerator {
	public abstract Calendar getInstance();
}

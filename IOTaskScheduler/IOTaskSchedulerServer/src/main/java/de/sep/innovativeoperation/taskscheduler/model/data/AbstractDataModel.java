package de.sep.innovativeoperation.taskscheduler.model.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;




@SuppressWarnings("serial")
public abstract class AbstractDataModel implements Serializable{
	
	@JsonProperty(value="ID")
	public abstract int getId();
	
	@JsonProperty(value="ID")
	public abstract void setId(int id);
}

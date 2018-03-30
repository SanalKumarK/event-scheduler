package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

public class Track {
	private List<Event> events = new ArrayList<Event>();
	private int totalDuration;
	
	public List<Event> getEvents() {
		return events;
	}
	
	public int getTotalDuration() {
		return totalDuration;
	}
	
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(); 
		strBuilder.append("Total Duration : "). append(totalDuration)
			.append("-").append(events);		
		return strBuilder.toString();
	}
}

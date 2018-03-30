package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;

public class Track {
	public ArrayList<Event> events = new ArrayList<>();
	public int totalDuration;
	
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder(); 
		strBuilder.append("Total Duration : "). append(totalDuration)
			.append("-").append(events);		
		return strBuilder.toString();
	}
}

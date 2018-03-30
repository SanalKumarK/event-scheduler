package com.thoughtworks.solution.eventscheduler;

import junit.framework.Assert;

public class EventBuilder {
	
	private EventBuilder() {}
	
	public static final String LUNCH = "Lunch";
	public static final String NETWORKING = "Networking Event";
	
	public static Event createEvent(String type) {
		
		Assert.assertNotNull("Type should not be null", type);
		
		Event event = null;		
		switch(type) {
			case LUNCH :  
				event = new Event(LUNCH, true, 60);
				break;
			
			case NETWORKING : 
				event = new Event(NETWORKING, true, 60);
				break;
				
			default :
				break;
		}
		return event;
	}
}

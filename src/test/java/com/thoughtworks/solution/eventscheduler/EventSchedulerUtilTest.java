package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jdk.Exported;
import junit.framework.TestCase;

public class EventSchedulerUtilTest extends TestCase {

	public EventSchedulerUtilTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testConvertToEvent() {
		String input = "Lua for the Masses 30min";
		Event event = EventSchedulerUtil.convertToEvent(input);
		assertEquals(event.getName(), "Lua for the Masses");
		assertEquals(event.getDuration(), 30);
		
		String input1 = "Rails for Python Developers lightning";
		Event event1 = EventSchedulerUtil.convertToEvent(input1);
		assertEquals(event1.getName(), "Rails for Python Developers");
		assertTrue(event1.isLightning());
		
		String input2 = "LinuxShell40mins";
		assertNull(EventSchedulerUtil.convertToEvent(input2));
				
		assertNull(EventSchedulerUtil.convertToEvent(null));
	}
	
	public void testTrimEventsListOfEventEvent() {
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(new Event("Sit Down and Write", 30, false));
		events.add(new Event("Sit Down and Write 2", 50, false));
		
		Event nwEvent = new Event("Networking Event", true, 60);
		List<Event> result = EventSchedulerUtil.trimEvents(events, nwEvent);
		assertTrue(result.size() == 2);
		
		events.add(nwEvent);
		assertTrue(events.size() == 3);
		result = EventSchedulerUtil.trimEvents(events, nwEvent);
		
		assertTrue("Since event is cloned in the method, "
				+ "size of events should be same after removal",events.size() == 3); 
		
		assertTrue("Failed to trim the array", result.size() == 2);		
	}
	
	@Test(expected=Exception.class)
	public void testTrimEventsListOfEventEventException() {
		//Generate illegal argument exception when called with null
		Event nwEvent = new Event("Networking Event", true, 60); 
		try {
			EventSchedulerUtil.trimEvents(null, nwEvent);	
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void testTrimEventsListOfEventListOfEvent() {
		ArrayList<Event> events = new ArrayList<Event>();
		events.add(new Event("Sit Down and Write", 30, false));
		events.add(new Event("Sit Down and Write 2", 50, false));
				
		List<Event> result = EventSchedulerUtil.trimEvents(events, (List<Event>)events.clone());
		assertTrue(result.size() == 0);
		
	}

	public void testConvertToScheduledChart() {
		//fail("Not yet implemented");
	}

}

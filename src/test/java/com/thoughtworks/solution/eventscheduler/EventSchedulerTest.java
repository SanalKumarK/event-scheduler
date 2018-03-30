package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class EventSchedulerTest extends TestCase {

	public EventSchedulerTest(String name) {
		super(name);
	}

	ArrayList<String> inputs = new ArrayList<String>();
	
	EventScheduler scheduler = new EventScheduler();
	
	protected void setUp() throws Exception {
		super.setUp();
				
        inputs.add("Writing Fast Tests Against Enterprise Rails 60min");
        inputs.add("Overdoing it in Python 45min");
        inputs.add("Lua for the Masses 30min");
        inputs.add("Ruby Errors from Mismatched Gem Versions 45min");
        inputs.add("Common Ruby Errors 45min");
        inputs.add("Rails for Python Developers lightning");
        inputs.add("Communicating Over Distance 60min");
        inputs.add("Accounting-Driven Development 45min");
        inputs.add("Woah 30min");
        inputs.add("Sit Down and Write 30min");
        inputs.add("Pair Programming vs Noise 45min");
        inputs.add("Rails Magic 60min");
        inputs.add("Ruby on Rails: Why We Should Move On 60min");
        inputs.add("Clojure Ate Scala (on my project) 45min");
        inputs.add("Programming in the Boondocks of Seattle 30min");
        inputs.add("Ruby vs. Clojure for Back-End Development 30min");
        inputs.add("Ruby on Rails Legacy App Maintenance 60min");
        inputs.add("A World Without HackerNews 30min");
        inputs.add("User Interface CSS in Rails Apps 30min");        
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testSchedule() {
		System.out.println("***********Test Input***********");		
		for (String string : inputs) {
			System.out.println(string);
		}
		
		System.out.println("***********Test Output***********");
        List<String> scheduledChart = scheduler.schedule(inputs);
        assertNotNull("result of schedule() should not be null.",scheduledChart);        
        for (String event : scheduledChart) {
			System.out.println(event);
		}
	}
	
	public void testScheduleForNull() {
		assertEquals("schedule() should return empty array, on null or empty input",
				0, scheduler.schedule(null).size()); 
	}

	public void testGetScheduleEvents() {
		ArrayList<Event> events = new ArrayList<>();
		events.add(new Event("event1", 60, false));
		events.add(new Event("event2", 30, false));
		events.add(new Event("event2", 60, false));
		
		Track track = scheduler.getScheduleEvents(events,180);
		assertNotNull(track);
		assertEquals(track.getEvents().size(), 3);
		assertEquals(track.getTotalDuration(), 150);
		
		track = scheduler.getScheduleEvents(events,150);
		assertNotNull(track);
		assertEquals(track.getEvents().size(), 3);
		assertEquals(track.getTotalDuration(), 150);
		
		track = scheduler.getScheduleEvents(events,100);
		assertNotNull(track);
		assertEquals(track.getEvents().size(), 2);
		assertEquals(track.getTotalDuration(), 90);
		
		track = scheduler.getScheduleEvents(events,60);
		assertNotNull(track);
		assertEquals(track.getEvents().size(), 1);
		assertEquals(track.getTotalDuration(), 60);
		
		track = scheduler.getScheduleEvents(events,20);
		assertNotNull(track);
		assertEquals(track.getEvents().size(), 0);
		assertEquals(track.getTotalDuration(), 0);
	}

}

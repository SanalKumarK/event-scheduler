package com.thoughtworks.solution.eventscheduler;

import junit.framework.TestCase;

public class EventBuilderTest extends TestCase {

	public void testCreateEvent() {
		Event lunchEvent = EventBuilder.createEvent(EventBuilder.LUNCH);
		assertEquals(lunchEvent.getName(), EventBuilder.LUNCH);
		assertTrue("Lunch Event should be break event", lunchEvent.isBreakEvent());
		
		Event nwevent = EventBuilder.createEvent(EventBuilder.NETWORKING);
		assertEquals(nwevent.getName(), EventBuilder.NETWORKING);
		assertTrue("Networking Event should be break event", nwevent.isBreakEvent());
		
		assertNull(EventBuilder.createEvent("default"));
	}
}

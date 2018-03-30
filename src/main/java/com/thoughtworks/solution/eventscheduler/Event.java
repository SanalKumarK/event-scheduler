package com.thoughtworks.solution.eventscheduler;

/**
 * 
 * @author Sanal
 *
 */
class Event {
	private String name;
	private int duration;
	private boolean lightning;
	private boolean breakEvent;
	
	public Event(String name, int duration, boolean lightning) {
		this.name = name;
		this.duration = duration;
		this.lightning = lightning;
	}
	
	public Event(String name, boolean breakEvent, int duration) {
		this.name = name;
		this.duration = duration;
		this.breakEvent = breakEvent;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDuration() {
		return duration;
	}
		
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ");
		if(lightning) {
			sb.append(App.LIGHTNING_STR);
		} else {
			if(!breakEvent) {
				sb.append(duration).append(App.TIME_UNIT);	
			}
		}
		return sb.toString();
	}
}


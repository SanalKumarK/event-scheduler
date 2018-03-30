package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

public class EventScheduler {	
	
	public List<String> schedule(List<String> inputs) {
		
		List<String> tracksList = new ArrayList<>();;
		
		if(inputs == null || inputs.isEmpty()) {			
			return tracksList;
		}		
		
		List<Event> events = new ArrayList<>(); 
        for (String input : inputs) {        	
        	Event event = EventSchedulerUtil.convertToEvent(input);
        	if(event != null) {
        		events.add(event);	
        	}
		}
                
        if(!events.isEmpty()) {
        	List<Track> tracks = scheduleEvents(events);
        	if(!tracks.isEmpty()) {
        		tracksList = EventSchedulerUtil.convertToScheduledChart(tracks);	
            }
        }
        return tracksList;
	}
	
	public List<Track> scheduleEvents(List<Event> tempEvents) {
        
        List<Track> tracks = new ArrayList<>();
        while(!tempEvents.isEmpty()) {
        	Track track = new Track();
        	Track mngTrack = getScheduleEvents(tempEvents, App.MORNING_SESSION_DURATION);
        	track.getEvents().addAll(mngTrack.getEvents());
        	track.setTotalDuration(track.getTotalDuration() + mngTrack.getTotalDuration());
        	Event lunch = EventBuilder.createEvent(EventBuilder.LUNCH);
        	track.getEvents().add(lunch);
        	track.setTotalDuration(track.getTotalDuration() + lunch.getDuration());        	        	
        	
        	EventSchedulerUtil.trimEvents(tempEvents, track.getEvents());        	
        	
        	Track evenTrack = getScheduleEvents(tempEvents, App.AFTERNOON_SESSION_DURATION);
        	track.getEvents().addAll(evenTrack.getEvents());
        	track.setTotalDuration(track.getTotalDuration() + evenTrack.getTotalDuration());
        	Event networking = EventBuilder.createEvent(EventBuilder.NETWORKING);
        	track.getEvents().add(networking);
        	track.setTotalDuration (track.getTotalDuration() + networking.getDuration());
        	
        	tracks.add(track);
        	
        	EventSchedulerUtil.trimEvents(tempEvents, evenTrack.getEvents());
        }
        return tracks;
	}
	
	public Track getScheduleEvents(List<Event> events, int reqDuration) {
		if(events == null || events.isEmpty()) {
			return new Track();
		} else {
			return findEvents(events, reqDuration);
		}
	}
	
	private Track findEvents(List<Event> events, int reqDuration) {
		Track result = new Track();
		
		if(reqDuration == 0) {
			return result;
		} else {
			Event curEvent = events.get(0);
			if(curEvent.getDuration() == reqDuration) {
				result.getEvents().add(curEvent);
				result.setTotalDuration (reqDuration);
			} else {
				List<Event> remainingEvents = EventSchedulerUtil.trimEvents(events, curEvent);
				if(!remainingEvents.isEmpty()) {
					Track incEvent = findEvents(remainingEvents, reqDuration-curEvent.getDuration());
					Track excEvent = findEvents(remainingEvents, reqDuration);
										
					int incDur = incEvent.getTotalDuration() + curEvent.getDuration();
					int excDur =  excEvent.getTotalDuration();
					
					int bestFit = Math.min(Math.max(incDur, excDur), reqDuration);
					
					if(incDur == bestFit) {
						incEvent.getEvents().add(0, curEvent);
						incEvent.setTotalDuration (incDur);
						return incEvent;
					}
					
					if(excDur == bestFit) {
						return excEvent;
					}					
				} else {
					//If last element, check if the event duration is less than the required duration,
					//if so add it to the result
					if(curEvent.getDuration() < reqDuration) {
						result.getEvents().add(curEvent);
						result.setTotalDuration (curEvent.getDuration());
						return result;	
					}
				}
			}
		}
		return result;
	}	
}

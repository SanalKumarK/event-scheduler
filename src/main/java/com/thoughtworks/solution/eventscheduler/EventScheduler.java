package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

public class EventScheduler {	
	
	public List<String> schedule(List<String> inputs) {
		ArrayList<Event> events = new ArrayList<>();        
        for (String input : inputs) {
			events.add(EventSchedulerUtil.convertToEvent(input));
		}
        
        List<Track> tracks = scheduleEvents(events);               
        
        return EventSchedulerUtil.convertToScheduledChart(tracks);        
	}
	
	public List<Track> scheduleEvents(ArrayList<Event> tempEvents) {
        
        List<Track> tracks = new ArrayList();
        while(!tempEvents.isEmpty()) {
        	Track track = new Track();
        	Track mngTrack = getScheduleEvents(tempEvents, 180);
        	track.events.addAll(mngTrack.events);
        	track.totalDuration += mngTrack.totalDuration;
        	Event lunch = EventBuilder.createEvent(EventBuilder.LUNCH);
        	track.events.add(lunch);
        	track.totalDuration += lunch.getDuration();        	        	
        	
        	EventSchedulerUtil.trimEvents(tempEvents, track.events);        	
        	
        	Track evenTrack = getScheduleEvents(tempEvents, 240);
        	track.events.addAll(evenTrack.events);
        	track.totalDuration += evenTrack.totalDuration;
        	Event networking = EventBuilder.createEvent(EventBuilder.NETWORKING);
        	track.events.add(networking);
        	track.totalDuration += networking.getDuration();
        	
        	tracks.add(track);
        	
        	EventSchedulerUtil.trimEvents(tempEvents, evenTrack.events);
        }
        return tracks;
	}
	
	private Track getScheduleEvents(List<Event> events, int reqDuration) {
		Track result = new Track();
		
		if(reqDuration == 0) {
			return result;
		} else {
			Event curEvent = events.get(0);
			if(curEvent.getDuration() == reqDuration) {
				result.events.add(curEvent);
				result.totalDuration = reqDuration;
			} else {
				List<Event> remainingEvents = EventSchedulerUtil.trimEvents(events, curEvent);
				if(!remainingEvents.isEmpty()) {
					Track incEvent = getScheduleEvents(remainingEvents, reqDuration-curEvent.getDuration());
					Track excEvent = getScheduleEvents(remainingEvents, reqDuration);
										
					int incDur = incEvent.totalDuration + curEvent.getDuration();
					int excDur =  excEvent.totalDuration ;
					
					int bestFit = Math.min(Math.max(incDur, excDur), reqDuration);
					
					if(incDur == bestFit) {
						incEvent.events.add(0, curEvent);
						incEvent.totalDuration = incDur;
						return incEvent;
					}
					
					if(excDur == bestFit) {
						return excEvent;
					}					
				} else {
					//If last element, check if the event duration is less than the required duration,
					//if so add it to the result
					if(curEvent.getDuration() < reqDuration) {
						result.events.add(curEvent);
						result.totalDuration = curEvent.getDuration();
						return result;	
					}
				}
			}
		}
		return result;
	}	
}

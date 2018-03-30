package com.thoughtworks.solution.eventscheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventSchedulerUtil {
		
	public static Event convertToEvent(String input) {		
		try{
			Event event = null;
			int splitIndex = input.lastIndexOf(' ');
			String name = input.substring(0, splitIndex);
			String time = input.substring(splitIndex).trim();
			if(time.equals(App.LIGHTNING_STR)) {
				event = new Event(name, App.LIGHTNING, true);
			} else {
				String[] arr = time.split("min");
				int duration = Integer.parseInt(arr[0]);
				event = new Event(name, duration, false);	
			}
			return event;	
		} catch (Exception e) {
			System.err.println("Unable to parse the string input " + input); 
			System.err.println(e);
		}
		return null;
	}		

	public static List<Event> trimEvents(List<Event> arr, Event event) {
		List<Event> newArr = null;
		try {
			newArr = (List<Event>) ((ArrayList<Event>) arr).clone();
			newArr.remove(event);
			return newArr;	
		} catch(Exception ex) {
			System.err.println(ex);
		}
		return newArr;
	}
	
	public static List<Event> trimEvents(List<Event> arr, ArrayList<Event> objects) {
		for (Object object : objects) {
			arr.remove(object);			
		}
		return arr;	
	}

	public static List<String> convertToScheduledChart(List<Track> tracks) {
		List<String> chart = new ArrayList<>();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
		LocalTime time = LocalTime.of(9, 0);
		int i=0;
		for (Track track : tracks) {
			chart.add("Track " + (++i));
			for (Event event : track.events) {
				chart.add(String.format("%s %s", timeFormatter.format(time), event));
				time = time.plusMinutes(event.getDuration());
			}
			time = LocalTime.of(9, 0);
			chart.add("\n");
		}
		return chart;
	}
}

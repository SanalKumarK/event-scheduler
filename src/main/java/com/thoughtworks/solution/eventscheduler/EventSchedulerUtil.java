package com.thoughtworks.solution.eventscheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventSchedulerUtil {
		
	private EventSchedulerUtil() {}
	
	public static Event convertToEvent(String input) {
		Event event = null;
		try{
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
		} catch (Exception e) {
			System.err.println("Invalid Input : " + input); 			
		}
		return event;
	}		

	public static List<Event> trimEvents(List<Event> arr, Event event) {
		
		if(arr == null) {			
			throw new IllegalArgumentException("Trim Source Array should not be null");
		}
		
		if(event == null) {
			return arr;
		}
		
		List<Event> newArr = null;
		try {
			newArr = (List<Event>) ((ArrayList<Event>) arr).clone();
			newArr.remove(event);
			return newArr;	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return newArr;
	}
	
	public static List<Event> trimEvents(List<Event> arr, List<Event> objects) {
		
		if(arr == null) {			
			throw new IllegalArgumentException("Trim Source Array should not be null");
		}		
		
		if(objects == null) {
			return arr;
		}
		
		try {
			for (Object object : objects) {
				arr.remove(object);			
			}	
		} catch(Exception ex) {
			ex.printStackTrace();
		}		
		return arr;	
	}

	public static List<String> convertToScheduledChart(List<Track> tracks) {
		List<String> chart = new ArrayList<String>();
		
		if(tracks == null || tracks.isEmpty()) {
			System.out.println("No Tracks to convert");
			return chart;
		}
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
		LocalTime time = LocalTime.of(9, 0);
		int i=0;
		for (Track track : tracks) {
			chart.add("Track " + (++i));
			for (Event event : track.getEvents()) {
				chart.add(String.format("%s %s", timeFormatter.format(time), event));
				time = time.plusMinutes(event.getDuration());
			}
			time = LocalTime.of(9, 0);
		}
		return chart;
	}
}

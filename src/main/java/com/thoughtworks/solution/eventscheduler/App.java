package com.thoughtworks.solution.eventscheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String LIGHTNING_STR = "lightning"; //5 mins

	public static final int LIGHTNING = 5; //5 mins	

	public static final String TIME_UNIT = "mins";
	
    public static void main( String[] args )
    {        
        EventScheduler scheduler = new EventScheduler();
                       
        ArrayList<String> inputs = getInputs();
        
        List<String> scheduledChart = scheduler.schedule(inputs);
        
        for (String event : scheduledChart) {
			System.out.println(event);
		}
    }
    
    private static ArrayList<String> getInputs() {
    	ArrayList<String> inputs = new ArrayList<>();
    	
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
        
        return inputs;
    }
}

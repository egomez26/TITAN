package org.pnpl.solvers.cpntools.handlers;

import java.util.Comparator;

public class Event implements Comparable<Event>{
    private String stream;
    private int time;
    private int step;
    private Object value;
    /* Define "Unit", "Int" and "Object" as constants */
    private String type;
    
    public Event(){
        setStream("");
        setTime(-1);
        setStep(-1);
        setValue(new String(""));
        setType(new String("Object"));
    }

    public String toString() {
    	return "{" + getStream() + ", " + Integer.valueOf(getStep()).toString() + ", " + Integer.valueOf(getTime()).toString() + ", " + getValue().toString() + "}";
    }
    
    public boolean equals(Event te2) {
        /* Two events are equal if they happen at the same time, disregarding the step count */
		return te2.getStream().equals(this.stream) && 
				te2.getTime() == this.time &&
				te2.getValue().equals(this.value) &&
				te2.getType().equals(this.type);
    }

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public int compareTo(Event e2) {
		/* Sort by the execution step in the trace simulator. */
		int value =  this.getStep() - e2.getStep();
		/* In case that two events happen at the same time, 
		 * Event[Unit] (i.e., PN transitions) has higher priority. */
		if ((value == 0) && this.getType().equals("Unit") && !e2.getType().equals("Unit")) {
			value = -1;			
		}
		return value;
	}
}

class CustomComparator implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {
        // elements are sorted in reverse order
        int value =  e1.getTime() - e2.getTime();
        return value;
    }
}
package in.co.cultura.cultura18.Model;

import in.co.cultura.cultura18.Activities.Schedule;

/**
 * Created by admin on 17-02-18.
 */

public class ScheduleObject {
    private String eventName;
    private String time;
    private String location;

    public ScheduleObject(String eventName, String location, String time){
        this.eventName = eventName;
        this.location = location;
        this.time = time;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

}

package model;

import java.util.ArrayList;
import java.util.List;

public class Activity implements Event {
    private String name;
    private final List<Event> activities;

    public Activity(String name) {
        this.name = name;
        activities = new ArrayList<>();
    }

    public Activity(String name ,List<Event> activities ) {
        this.name = name;
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void describe() {
        System.out.println(name);
        if (!activities.isEmpty()) {
            System.out.println("  composé de ");
            for (Event event : activities) {
                System.out.print(" - ");
                event.describe();
            }
        }
    }
}

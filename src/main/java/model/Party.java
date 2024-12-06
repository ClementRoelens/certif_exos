package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Party implements Event {
    private String name;
    private LocalDate date;
    private String place;
    private List<Event> activities;
    private List<Guest> guests = new ArrayList<>();

    private Party(Builder builder) {
        this.name = builder.name;
        this.date = builder.date;
        this.place = builder.place;
        this.activities = builder.activities;
    }

    public void showGuests(){
        System.out.println("Les invités de " + name + " sont : ");
        for (Guest guest : guests) {
            System.out.println(guest.getName());
        }
    }

    public void addGuest(Guest guest){
        guests.add(guest);
    }

    public void removeGuest(Guest guest){
        guests.remove(guest);
    }

    @Override
    public void describe() {
        System.out.println(name + " a lieu à " + place + " le " + date + ".Il est composé de ");
        for (Event event : activities) {
            event.describe();
        }
    }

    public static class Builder{
        private String name;
        private LocalDate date;
        private String place;
        private List<Event> activities;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder date(LocalDate date){
            this.date = date;
            return this;
        }

        public Builder place(String place){
            this.place = place;
            return this;
        }

        public Builder activities(List<Event> activities){
            this.activities = activities;
            return this;
        }

        public Party build(){
            return new Party(this);
        }
    }
}

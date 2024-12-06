import command.AddClientCommand;
import command.Command;
import command.GuestControl;
import command.RemoveClientCommand;
import model.Activity;
import model.Event;
import model.Guest;
import model.Party;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Event> activities = List.of(
                new Activity("Concours de Mario Kart", List.of(
                        new Activity("Grand chelem 200cc"),
                        new Activity("Bataille de bob-ombs")
                )),
                new Activity("Défilé de cosplay"),
                new Activity("Dédicaces", List.of(
                        new Activity("Signatures"),
                        new Activity("Photos"),
                        new Activity("Free hugs")
                ))
        );

        Party party = new Party.Builder()
                .name("Geek days")
                .date(LocalDate.of(2024, 12, 15))
                .place("Grand palais")
                .activities(activities)
                .build();

        party.describe();

        Command addCommand = new AddClientCommand(party);
        Command removeCommand = new RemoveClientCommand(party);
        GuestControl control = new GuestControl();

        Guest clement = new Guest("Clément");
        Guest nassim = new Guest("Nassim");

        System.out.println("\nOn invite Clément et Nassim\n");

        control.setCommand(addCommand);
        control.execute(clement);
        control.execute(nassim);

        party.showGuests();

        System.out.println("\nEt maintenant on désinvite Nassim\n");

        control.setCommand(removeCommand);
        control.execute(nassim);

        party.showGuests();
    }
}

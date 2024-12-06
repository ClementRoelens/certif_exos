package command;

import model.Guest;
import model.Party;

public class AddClientCommand implements Command {
    private Party party;

    public AddClientCommand(Party party) {
        this.party = party;
    }

    @Override
    public void guestAction(Guest guest) {
        party.addGuest(guest);
    }
}

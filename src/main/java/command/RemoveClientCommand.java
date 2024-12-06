package command;

import model.Guest;
import model.Party;

public class RemoveClientCommand implements Command {
    private Party party;

    public RemoveClientCommand(Party party) {
        this.party = party;
    }

    @Override
    public void guestAction(Guest guest) {
        party.removeGuest(guest);
    }
}

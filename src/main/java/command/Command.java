package command;

import model.Guest;

public interface Command {
    void guestAction(Guest guest);
}

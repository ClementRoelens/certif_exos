package command;

import model.Guest;

public class GuestControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute(Guest guest) {
        command.guestAction(guest);
    }
}

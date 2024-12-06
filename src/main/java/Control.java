public class Control {
    private Command command;

  public void setCommand(Command command) {
      this.command = command;
  }

    public void pressButton(Song song) {
      command.selectSong(song);
    }
}

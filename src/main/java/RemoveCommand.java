public class RemoveCommand implements Command {
    private final Playlist playlist;

    public RemoveCommand(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void selectSong(Song song) {
        playlist.removeSong(song);
    }
}

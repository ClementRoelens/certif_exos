public class AddCommand implements Command {
    private final Playlist playlist;

    public AddCommand(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void selectSong(Song song) {
        playlist.addSong(song);
    }
}

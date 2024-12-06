import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final List<Song> list = new ArrayList<>();

    public void addSong(Song song) {
        list.add(song);
    }

    public void removeSong(Song song) {
        list.remove(song);
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("Playlist : \n");
        for (Song song : list) {
            response.append("- ")
                    .append(song.getName())
                    .append(" par ")
                    .append(song.getAuthor())
                    .append("\n");
        }

        return response.toString();
    }
}

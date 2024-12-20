package music;

public class Album {
    private String name;
    private String artist;
    private int year;

    public Album(String name, String artist, int year) {
        this.name = name;
        this.artist = artist;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", year=" + year +
                '}';
    }
}

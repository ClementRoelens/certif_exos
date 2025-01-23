package org.example.exostreamingtest.entity;

public class Song {
    private String title;
    private String genre;


    public Song() {}

    public Song(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}

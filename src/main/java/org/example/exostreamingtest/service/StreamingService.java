package org.example.exostreamingtest.service;

import org.example.exostreamingtest.entity.Song;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class StreamingService {
    private final List<Song> songs;

    public StreamingService() {
        songs = Arrays.asList(
                new Song("Bohemian rhapsody","Rock"),
                new Song("Hotel California","Rock"),
                new Song("Stairway to heaven","Rock"),
                new Song("Thriller","Pop"),
                new Song("Like a virgin","Pop"),
                new Song("We didn't start the fire","Pop"),
                new Song("Gangsta paradise","Rap"),
                new Song("The real Slim Shaddy","Rap"),
                new Song("Raelsan","Rap")
        );
    }


    public Flux<String> getPlaylist(String genre){
        List<String> titles = songs.stream()
                .filter(s -> s.getGenre().equalsIgnoreCase(genre))
                .map(Song::getTitle)
                .toList();

        if (titles.isEmpty()) {
            return Flux.error(new IllegalArgumentException("Le genre \"" + genre.toLowerCase() + "\" n'est pas supporté"));
        }
        return Flux.fromIterable(titles);
    }
}

package org.example.exostreamingtest.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class StreamingServiceTest {
    private final StreamingService streamingService = new StreamingService();

    @Test
    void testRock(){
        StepVerifier.create(streamingService.getPlaylist("rock"))
                .expectSubscription()
                .expectNext("Bohemian rhapsody")
                .expectNext("Hotel California")
                .expectNext("Stairway to heaven")
                .verifyComplete();
    }

    @Test
    void testPop(){
        StepVerifier.create(streamingService.getPlaylist("pop"))
                .expectSubscription()
                .expectNext("Thriller")
                .expectNext("Like a virgin")
                .expectNext("We didn't start the fire")
                .verifyComplete();
    }

    @Test
    void testRap(){
        StepVerifier.create(streamingService.getPlaylist("rap"))
                .expectSubscription()
                .expectNext("Gangsta paradise")
                .expectNext("The real Slim Shaddy")
                .expectNext("Raelsan")
                .verifyComplete();
    }

    @Test
    void testException(){
        StepVerifier.create(streamingService.getPlaylist("techno"))
                .expectSubscription()
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException
                    && throwable.getMessage().equals("Le genre \"techno\" n'est pas supporté"))
                .verify();
    }
}

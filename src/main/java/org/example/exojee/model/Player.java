package org.example.exojee.model;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private int id;
    private static int count = 1;
    private String name;
    private Map<Integer, Integer> gamesHistory;

    public Player(String name) {
        this.name = name;
        gamesHistory = new HashMap<>();
        id = count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getGamesHistory() {
        return gamesHistory;
    }

    public void setGamesHistory(Map<Integer, Integer> gamesHistory) {
        this.gamesHistory = gamesHistory;
    }

    public void addGame(Quizz quizz, int score) {
        gamesHistory.put(quizz.getId(), score);
    }

    public Integer getResultFromOneQuizz(int quizzId) {
        return gamesHistory.get(quizzId);
    }
}

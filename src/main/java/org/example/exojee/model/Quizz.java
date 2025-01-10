package org.example.exojee.model;

import java.util.HashMap;
import java.util.Map;

public class Quizz {
    private int id;
    private static int count = 1;
    private Map<Integer, String> questions;
    private Map<Integer, Integer> answers;


    public Quizz() {
        questions = new HashMap<>();
        answers = new HashMap<>();
        id = count++;
    }

    public Quizz(Map<Integer, String> questions,Map<Integer, Integer> answers) {
        this.questions = questions;
        this.answers = answers;
        id = count++;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Integer> answers) {
        this.answers = answers;
    }

    public Map<Integer, String> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<Integer, String> questions) {
        this.questions = questions;
    }
}

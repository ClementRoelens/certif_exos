package org.example.exojee.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.exojee.model.Quizz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class QuizzService {
    List<Quizz> quizzes;

    public QuizzService() {
        quizzes = new ArrayList<>();

        Map<Integer, String> questions = new HashMap<>();
        questions.put(1, """
                Qu'est-ce qu'un quizz ?
                1 - Une variété de légumes
                2 - Un jeu de questions/réponses
                """);
        questions.put(2, """
                Qui est le meilleur formateur d'Utopios ?
                1 - Ihab
                2 - Christophe
                3 - Mohammed
                4 - Léo
                """);
        Map<Integer, Integer> answers = new HashMap<>();
        answers.put(1, 2);
        answers.put(2, 2);

        addQuizz(new Quizz(questions, answers));
    }

    public List<Quizz> getQuizzes() {
        return quizzes;
    }

    public void addQuizz(Quizz quizz) {
        quizzes.add(quizz);
    }

    public Quizz getQuizz(int id) {
        return quizzes.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removeQuizz(int id) {
        quizzes.remove(getQuizz(id));
    }

    public int playQuizz(int quizzId, Map<Integer, Integer> answers){
        Quizz quizz = getQuizz(quizzId);
        int result = 0;

        for (Map.Entry<Integer, Integer> entry : quizz.getAnswers().entrySet()) {
            if (entry.getValue() == answers.get(entry.getKey())) {
                result++;
            }
        }

        return result;
    }


}

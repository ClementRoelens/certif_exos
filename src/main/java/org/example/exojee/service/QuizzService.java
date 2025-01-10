package org.example.exojee.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.example.exojee.DTO.Result;
import org.example.exojee.model.Quizz;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class QuizzService {
    private List<Quizz> quizzes;
    private final String QUIZZES_PATH;

    public QuizzService() {
        QUIZZES_PATH = "C:\\Users\\Administrateur\\Documents\\Exos\\exo-jee\\quizzes.ser";
        File file = new File(QUIZZES_PATH);

        if (!file.exists()) {
            System.out.println("File does not exist");
            try {
                file.createNewFile();
                System.out.println("File created");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(QUIZZES_PATH))){
            quizzes = (List<Quizz>) stream.readObject();
        } catch (Exception e) {
            quizzes = new ArrayList<>();
        }
    }

    public List<Quizz> getQuizzes() {
        return quizzes;
    }

    public boolean addQuizz(Quizz quizz) {
        quizzes.add(quizz);
        try {
            serializeQuizzes();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Quizz getQuizz(int id) {
        return quizzes.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean removeQuizz(int id) throws IOException {
        if (quizzes.remove(getQuizz(id))){
            serializeQuizzes();
            return true;
        }
        return false;
    }

    public Result playQuizz(int quizzId, Map<Integer, Integer> answers){
        Quizz quizz = getQuizz(quizzId);

        if (quizz == null) {
            return null;
        }

        Map<Integer, Boolean> resultsByQuestion = new HashMap<>();

        int overallResult = 0;

        for (Map.Entry<Integer, Integer> entry : quizz.getAnswers().entrySet()) {
            if (entry.getValue().equals(answers.get(entry.getKey()))) {
                overallResult++;
                resultsByQuestion.put(entry.getKey(), entry.getValue().equals(answers.get(entry.getKey())));
            }
        }

        return new Result(overallResult, resultsByQuestion);
    }

    private void serializeQuizzes() throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(QUIZZES_PATH))) {
            stream.writeObject(quizzes);
        }
    }

}

package org.example.exojee.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.exojee.model.Quizz;
import org.example.exojee.service.QuizzService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizzResource {

    private final QuizzService quizzService;

    @Inject
    public QuizzResource(QuizzService quizzService) {
        this.quizzService = quizzService;
    }

    @GET
    public List<Quizz> getQuizzes(){
        return quizzService.getQuizzes();
    }

    @GET
    @Path("/{id}")
    public Quizz getQuizzById(@PathParam("id") int id){
        return quizzService.getQuizz(id);
    }

    @POST
    public Quizz createQuizz(Quizz quizz){
        quizzService.addQuizz(quizz);
        return quizz;
    }

    @POST
    @Path("/{id}/play")
    public int playQuizz(@PathParam("id") int id, Map<Integer,Integer> answers){
        return quizzService.playQuizz(id, answers);
    }

    @DELETE
    @Path("{id}")
    public void deleteQuizz(@PathParam("id") int id){
        quizzService.removeQuizz(id);
    }
}

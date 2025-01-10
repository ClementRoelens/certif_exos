package org.example.exojee.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.exojee.DTO.QuizzDTO;
import org.example.exojee.DTO.Result;
import org.example.exojee.model.Quizz;
import org.example.exojee.service.QuizzService;

import java.io.IOException;
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
    public Response getQuizzes(){
        List<QuizzDTO> quizzes = quizzService.getQuizzes().stream()
                .map(q -> new QuizzDTO(q.getId(),q.getQuestions()))
                .toList();

        if (quizzes.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(quizzes).build();
    }

    @GET
    @Path("/{id}")
    public Response getQuizzById(@PathParam("id") int id){
        Quizz quizz = quizzService.getQuizz(id);

        if (quizz == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new QuizzDTO(quizz.getId(),quizz.getQuestions())).build();
    }

    @POST
    public Response createQuizz(Quizz quizz){
        if (quizzService.addQuizz(quizz)){
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Path("/{id}/play")
    public Response playQuizz(@PathParam("id") int id, Map<Integer,Integer> answers){
        Result result = quizzService.playQuizz(id, answers);

        if (result == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuizz(@PathParam("id") int id){
        try {
            if (quizzService.removeQuizz(id)){
                return Response.ok("Quizz supprimé").build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IOException e){
            return Response.serverError().build();
        }
    }
}

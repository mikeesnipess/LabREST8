package com.artur.rest.labrest8.resources;

import com.artur.rest.labrest8.beans.AuthBean;
import com.artur.rest.labrest8.beans.EvaluationBean;
import com.artur.rest.labrest8.dto.EvaluationDTO;
import com.artur.rest.labrest8.dto.UserDTO;
import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.entities.User;
import com.artur.rest.labrest8.service.EvaluationService;
import com.artur.rest.labrest8.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Path("/evaluations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluationResource {

    @Context SecurityContext securityContext;

    @Inject
    private EvaluationService evaluationService;
    @Inject
    private EvaluationBean evaluationBean;
    @Inject
    private AuthBean authBean;

    @Inject
    private UserService userService;

    @GET
    @Path("/getAll")
    @RolesAllowed({"Admin","Student"})
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluationsDTO();
    }
//    GET Teachers for dropdown studentEvaluation.jsp page
    @GET
    @Path("/teachers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getAllTeachers() {
        List<User> teachers = userService.getAllTeachers();
        return teachers.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername()))
                .collect(Collectors.toList());
    }


    @GET
    @Path("/getEvaluationId/{id}")
    @RolesAllowed({"Admin"}) // Only admin and user roles can access
    public Response getEvaluationById(@PathParam("id") UUID id) {
        EvaluationDTO evaluationDTO = evaluationService.getEvaluationId(id);
        if (evaluationDTO != null) {
            return Response.ok(evaluationDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/addEvaluation")
    @RolesAllowed("Admin") // Only admin role can access
    public Response addEvaluation(Evaluation evaluation) {
        EvaluationDTO createdEvaluation = evaluationService.addEvaluation(evaluation);
        return Response.status(Response.Status.CREATED).entity(createdEvaluation).build();
    }

    @PUT
    @Path("/update/{id}")
    @RolesAllowed("Admin") // Only admin role can access
    public Response updateEvaluation(@PathParam("id") UUID id, Evaluation evaluation) {
        EvaluationDTO updatedEvaluation = evaluationService.updateEvaluation(id, evaluation);
        if (updatedEvaluation != null) {
            return Response.ok(updatedEvaluation).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed("Admin") // Only admin role can access
    public Response deleteEvaluation(@PathParam("id") UUID id) {
        boolean deleted = evaluationService.deleteEvaluation(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/submitEvaluation")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @RolesAllowed({"Admin", "Student"})
    public Response submitEvaluation(
            @FormParam("activity") String activity,
            @FormParam("activityType") String activityType,
            @FormParam("grade") int grade,
            @FormParam("comment") String comment,
            @FormParam("teacher") UUID receivedTeacher
    ) {
        Evaluation evaluation = new Evaluation();
        evaluation.setActivity(activity);
        evaluation.setActivityType(activityType);
        evaluation.setGrade(grade);
        evaluation.setComment(comment);
        User teacher = evaluationService.getTeacherById(receivedTeacher);
        evaluation.setTeacher(teacher);
        User student = evaluationService.getUserByName(securityContext.getUserPrincipal().getName());
        evaluation.setStudent(student);

        evaluationService.submitEvaluation(evaluation);  // Save to DB
        return Response.ok("Evaluation submitted successfully!").build();
    }


}

package com.artur.rest.labrest8.resources;

import com.artur.rest.labrest8.dto.EvaluationDTO;
import com.artur.rest.labrest8.entities.Evaluation;
import com.artur.rest.labrest8.service.EvaluationService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/evaluations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EvaluationResource {

    @Inject
    private EvaluationService evaluationService;

    @GET
    @Path("/getAll")
    @RolesAllowed({"Admin","Student"})
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluationsDTO();
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
}

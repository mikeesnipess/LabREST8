package com.artur.rest.labrest8;

import com.artur.rest.labrest8.service.EvaluationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    @Inject
    private EvaluationService evaluationService;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
//
//    @GET
//    @Path("/getAll")
//    public String getAll() {return evaluationService.getName();}


}
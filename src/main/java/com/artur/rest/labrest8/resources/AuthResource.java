package com.artur.rest.labrest8.resources;

import com.artur.rest.labrest8.beans.AuthBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.net.URI;
import java.security.Principal;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

public class AuthResource {
    @Context
    SecurityContext securityContext;

    @Inject
    private AuthBean authBean;

    @GET
    @Path("/register")
    public String registerNewUser(@QueryParam("username") String username,
                                  @QueryParam("password") String password,
                                  @QueryParam("role") String role) {
        System.out.println("Registering new user: " + username);
        authBean.setUsername(username);
        authBean.setPassword(password);
        authBean.setRole(role);
        return authBean.register();
    }

    @GET
    @Path("/login")
    public Response loginUser() {
        String username = securityContext.getUserPrincipal().getName();
        Principal principal = securityContext.getUserPrincipal();

        authBean.setUsername(username);
        // Assuming the login method returns the response that you want to send.
        String responseContent = authBean.login();

        // After login, redirect to /receivedResponse with the response content
        return Response.seeOther(URI.create("/LabREST8_war_exploded/"+responseContent))
                .header("Received-Content", responseContent)  // Optionally, you can pass the received content as a header
                .build();
    }
}

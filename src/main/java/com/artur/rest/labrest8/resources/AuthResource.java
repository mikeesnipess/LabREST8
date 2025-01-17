package com.artur.rest.labrest8.resources;

import com.artur.rest.labrest8.beans.AuthBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.glassfish.jersey.process.internal.RequestContext;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

public class AuthResource {
    @Context
    SecurityContext securityContext;

    @Context
    RequestContext requestContext;

    @Inject
    private AuthBean authBean;

    @GET
    @Path("/register")
    public Response registerNewUser(@QueryParam("username") String username,
                                    @QueryParam("password") String password,
                                    @QueryParam("role") String role) {
        System.out.println("Registering new user: " + username);
        authBean.setUsername(username);
        authBean.setPassword(password);
        authBean.setRole(role);

        String resultPage = authBean.register();
        if(resultPage.equals("login")) {
            return Response.seeOther(URI.create("auth/" + resultPage)).build();
        }
        else
        {
            return Response.seeOther(URI.create("/LabREST8_war_exploded/" + resultPage)).build();
        }
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
//
//    @GET
//    @Path("/logout")
//    public Response logoutUser() {
//        this.securityContext = null;
//        // Redirect to the login page or a confirmation page
//        return Response.seeOther(URI.create("/LabREST8_war_exploded/index.jsp")).build();
//    }
}

package org.acme;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadFile(byte[] octects) {
        if (octects == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Le formulaire est invalide.").build();
        }
        int fileSize = octects.length;

        return Response.ok("La taille du fichier est de " + fileSize + " octet.", MediaType.TEXT_PLAIN)
            .build();
    }
}

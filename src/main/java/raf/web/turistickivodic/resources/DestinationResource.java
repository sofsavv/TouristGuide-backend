package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.Destination;
import raf.web.turistickivodic.services.DestinationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/destinations")
public class DestinationResource {

    @Inject
    private DestinationService destinationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(this.destinationService.allDestinations()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.destinationService.findDestination(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Destination destination) {
        try {
            this.destinationService.addDestination(destination);
            return Response.status(Response.Status.CREATED).entity(destination).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid Destination destination) {
        try {
            this.destinationService.updateDestination(id, destination);
            return Response.ok(destination).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            this.destinationService.deleteDestination(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}

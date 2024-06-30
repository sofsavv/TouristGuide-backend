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
    public Response all() {
        return Response.ok(this.destinationService.allDestinations()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Destination create(@Valid Destination destination) {
        return this.destinationService.addDestination(destination);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination find(@PathParam("id") Integer id) {
        return this.destinationService.findDestination(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination update(@PathParam("id") Integer id, @Valid Destination destination) {
        return this.destinationService.updateDestination(id, destination);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.destinationService.deleteDestination(id);
    }

}

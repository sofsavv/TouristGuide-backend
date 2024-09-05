package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.User;
import raf.web.turistickivodic.requests.LogInRequest;
import raf.web.turistickivodic.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource{

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LogInRequest logInRequest){

        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(logInRequest.getEmail(), logInRequest.getPassword());

        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);
        response.put("email", logInRequest.getEmail());
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("currentPage") @DefaultValue("1") int currentPage,
                            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return Response.ok(this.userService.allUsers(currentPage, pageSize)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid User user) {
        try {
            this.userService.addUser(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("email") String email) {
        return Response.ok(this.userService.findUser(email)).build();
    }
    @PUT
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("email") String email, @Valid User updatedUser) {
        try {
            this.userService.updateUser(email, updatedUser);
            return Response.ok(updatedUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{email}")
    public Response delete(@PathParam("email") String email) {
        try {
            this.userService.deleteUser(email);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{userId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserStatus(@PathParam("userId") String user) {
        try {
            this.userService.changeStatus(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating user status: " + e.getMessage()).build();
        }
    }



}

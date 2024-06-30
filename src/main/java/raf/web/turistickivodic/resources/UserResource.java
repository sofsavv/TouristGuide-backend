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
    public Response all() {
        return Response.ok(this.userService.allUsers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User create(@Valid User destination) {
        return this.userService.addUser(destination);
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("email") String email) {
        return this.userService.findUser(email);
    }
    @PUT
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public User update(@PathParam("email") String email, @Valid User updatedUser) {
        return this.userService.updateUser(email, updatedUser);
    }
    @DELETE
    @Path("/{email}")
    public void delete(@PathParam("email") String email) {
        this.userService.deleteUser(email);
    }



}

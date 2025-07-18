package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.Activity;
import raf.web.turistickivodic.services.ActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activities")
public class ActivityResource {

    @Inject
    private ActivityService activityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@QueryParam("currentPage") @DefaultValue("1") int currentPage,
                            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return Response.ok(this.activityService.allActivities(currentPage, pageSize)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Activity activity) {
        try {
            this.activityService.addActivity(activity);
            return Response.status(Response.Status.CREATED).entity(activity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Activity activity) {
        try {
            this.activityService.updateActivity(id, activity);
            return Response.ok(activity).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            this.activityService.deleteActivity(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/article/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActivitiesByArticleId(@PathParam("articleId") int articleId) {
        List<Activity> activities = this.activityService.findActivitiesByArticleId(articleId);
        return Response.ok(activities).build();
    }

}

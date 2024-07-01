package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.Comment;
import raf.web.turistickivodic.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/articles/{articleId}/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@PathParam("articleId") Integer articleId) {
        return Response.ok(this.commentService.allComments(articleId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("articleId") Integer articleId,@Valid Comment comment) {
        try {
            this.commentService.addComment(articleId, comment);
            return Response.status(Response.Status.CREATED).entity(comment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            this.commentService.deleteComment(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


}

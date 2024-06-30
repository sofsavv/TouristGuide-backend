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
    public Response all(@PathParam("articleId") Integer articleId) {
        return Response.ok(this.commentService.allComments(articleId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment create(@PathParam("articleId") Integer articleId, @Valid Comment comment) {
        return this.commentService.addComment(articleId, comment);
    }


}

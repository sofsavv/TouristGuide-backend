package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.services.ArticleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articles")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(
            @QueryParam("currentPage") @DefaultValue("1") int currentPage,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return Response.ok(this.articleService.allArticles(currentPage, pageSize)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid Article article) {
        try {
            this.articleService.addArticle(article);
            return Response.status(Response.Status.CREATED).entity(article).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Integer id) {
        return Response.ok(this.articleService.findArticle(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, @Valid Article article){
        try {
            this.articleService.updateArticle(id, article);
            return Response.ok(article).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/incr")
    @Produces(MediaType.APPLICATION_JSON)
    public Response incrementViews(@PathParam("id") Integer id) {
        try {
            articleService.incrementViews(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        try {
            this.articleService.deleteArticle(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDestination(@QueryParam("destination") Integer destinationId) {
        if (destinationId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Destination ID is required").build();
        }
        List<Article> articles = this.articleService.findArticlesByDestination(destinationId);
        return Response.ok(articles).build();
    }

    @GET
    @Path("/most-read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMostReadArticles() {
        List<Article> articles = this.articleService.findMostReadArticles();
        return Response.ok(articles).build();
    }




}

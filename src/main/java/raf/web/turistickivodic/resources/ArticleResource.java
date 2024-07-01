package raf.web.turistickivodic.resources;

import raf.web.turistickivodic.entities.Article;
import raf.web.turistickivodic.services.ArticleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/articles")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(this.articleService.allArticles()).build();
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

}

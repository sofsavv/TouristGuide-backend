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
    public Response all() {
        return Response.ok(this.articleService.allArticles()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Article create(@Valid Article destination) {
        return this.articleService.addArticle(destination);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article find(@PathParam("id") Integer id) {
        return this.articleService.findArticle(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article update(@PathParam("id") Integer id, @Valid Article article){
        return this.articleService.updateArticle(id, article);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.articleService.deleteArticle(id);
    }



}

package raf.web.turistickivodic.filters;

import raf.web.turistickivodic.resources.ArticleResource;
import raf.web.turistickivodic.resources.DestinationResource;
import raf.web.turistickivodic.resources.UserResource;
import raf.web.turistickivodic.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!this.isAuthRequired(requestContext)) {
            return;
        }

        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }
            if (!this.userService.isAuthorized(token, requestContext.getUriInfo().getPath())) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext){

        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getRequest().getMethod();

        if (path.contains("login")) {
            System.out.println("login");
            return false;
        }

        if ("GET".equals(method) && path.startsWith("api/articles")) {
            System.out.println("ARTICLES");
            return false;
        }

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();

        for (Object matchedResource : matchedResources) {
            System.out.println("matchedResource: "+matchedResource);
            if (matchedResource instanceof UserResource || matchedResource instanceof DestinationResource) {
                return true;
            }
        }
        return false;

    }
}

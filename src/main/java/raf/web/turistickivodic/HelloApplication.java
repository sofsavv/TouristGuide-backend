package raf.web.turistickivodic;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import raf.web.turistickivodic.repositories.activity.ActivityRepository;
import raf.web.turistickivodic.repositories.activity.MySqlActivityRepository;
import raf.web.turistickivodic.repositories.article.ArticleRepository;
import raf.web.turistickivodic.repositories.article.MySqlArticleRepository;
import raf.web.turistickivodic.repositories.comment.CommentRepository;
import raf.web.turistickivodic.repositories.comment.MySqlCommentRepository;
import raf.web.turistickivodic.repositories.destination.DestinationRepository;
import raf.web.turistickivodic.repositories.destination.MySqlDestRepository;
import raf.web.turistickivodic.repositories.user.MySqlUserRepository;
import raf.web.turistickivodic.repositories.user.UserRepository;
import raf.web.turistickivodic.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlDestRepository.class).to(DestinationRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlArticleRepository.class).to(ArticleRepository.class).in(Singleton.class);
                this.bind(MySqlActivityRepository.class).to(ActivityRepository.class).in(Singleton.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(DestinationService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(ArticleService.class);
                this.bindAsContract(ActivityService.class);
            }
        };
        register(binder);
        packages("raf.web.turistickivodic");
    }

}
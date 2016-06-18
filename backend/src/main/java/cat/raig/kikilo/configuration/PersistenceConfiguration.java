package cat.raig.kikilo.configuration;

import cat.raig.kikilo.infrastructure.Auth;
import cat.raig.kikilo.repository.HttpRequestInitializerYouTube;
import cat.raig.kikilo.repository.UserRepository;
import cat.raig.kikilo.entities.User;
import cat.raig.kikilo.repository.YouTubeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Configuration
public class PersistenceConfiguration {
  private Logger logger = Logger.getLogger(PersistenceConfiguration.class);

  @Autowired
  private UserRepository repository;

  @Bean
  public YouTubeRepository youTubeRepository() {
    return new cat.raig.kikilo.infrastructure.YouTubeRepository(Auth.HTTP_TRANSPORT,
            Auth.JSON_FACTORY, new HttpRequestInitializerYouTube());
  }


  @PostConstruct
  public void initializeDatabase() {
    logger.info("Creating users");
    repository.save(new User("admin", "admin","myemail", asList("VIEW", "EDIT", "ADD")));
  }
}

package cat.raig.kikilo.configuration;

import cat.raig.kikilo.repository.UserRepository;
import cat.raig.kikilo.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Configuration
public class PersistenceConfiguration {
  private Logger logger = Logger.getLogger(PersistenceConfiguration.class);

  @Autowired
  private UserRepository repository;

  @PostConstruct
  public void initializeDatabase() {
    logger.info("Creating users");
    repository.save(new User("admin", "admin","myemail", asList("VIEW", "EDIT", "ADD")));
  }
}

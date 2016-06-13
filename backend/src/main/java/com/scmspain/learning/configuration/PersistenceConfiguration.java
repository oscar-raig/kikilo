package com.scmspain.learning.configuration;

import com.scmspain.learning.entities.User;
import com.scmspain.learning.repository.UserRepository;
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
        repository.save(new User("admin", "admin", asList("VIEW", "EDIT", "ADD")));
    }
}

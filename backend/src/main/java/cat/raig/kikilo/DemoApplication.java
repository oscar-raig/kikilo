package cat.raig.kikilo;

import cat.raig.kikilo.configuration.PersistenceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@Import(PersistenceConfiguration.class)
public class DemoApplication {
  public static void main(String[] args) {
    ApplicationContext context =
      new ClassPathXmlApplicationContext("Beans.xml");
    SpringApplication.run(DemoApplication.class, args);
  }
}

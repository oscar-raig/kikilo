package cat.raig.kikilo.repository;

import cat.raig.kikilo.entities.Framework;
import cat.raig.kikilo.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FrameworkRepository extends CrudRepository<Framework, Long> {
  List<Framework> findByOwner(User owner);
}

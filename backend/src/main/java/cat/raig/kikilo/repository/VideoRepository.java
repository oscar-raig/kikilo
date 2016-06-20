package cat.raig.kikilo.repository;

import cat.raig.kikilo.entities.User;
import cat.raig.kikilo.entities.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {
  Video findByid(Long id);
  List<Video> findByOwner(User owner);
}

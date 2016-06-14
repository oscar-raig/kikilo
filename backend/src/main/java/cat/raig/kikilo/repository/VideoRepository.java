package cat.raig.kikilo.repository;

import cat.raig.kikilo.entities.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
  Video findByid(Long id);
}

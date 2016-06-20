package cat.raig.kikilo.repository;

import cat.raig.kikilo.entities.Video;

import java.util.List;

public interface  YouTubeRepository {
  List<Video> findByKeyWords(String keyWords);

  Video getVideoById(String id);
}

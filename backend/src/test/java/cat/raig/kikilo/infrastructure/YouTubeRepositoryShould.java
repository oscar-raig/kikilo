package cat.raig.kikilo.infrastructure;


import cat.raig.kikilo.entities.Video;
import cat.raig.kikilo.repository.HttpRequestInitializerYouTube;
import org.junit.Test;

import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class YouTubeRepositoryShould {

  @Test
  public void sampleUsage() {
    YouTubeRepository youTubeRepository = new YouTubeRepository(Auth.HTTP_TRANSPORT,
      Auth.JSON_FACTORY, new HttpRequestInitializerYouTube());
    List<Video> videos = youTubeRepository.findByKeyWords("rojo");

    assertThat(videos.size(),is(25));
  }

}

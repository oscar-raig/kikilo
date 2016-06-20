package cat.raig.kikilo.entities;


import cat.raig.kikilo.entities.Video;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class VideoShould {

  private static final String A_TITLE = "a title";
  private static final String A_URL = "a url";

  @Test
  public void constructorWorks() {
    Video video = new Video(A_TITLE, A_URL);
    assertThat(video.getTitle(), is(A_TITLE));
    assertThat(video.getId(), is(notNullValue()));
  }

}

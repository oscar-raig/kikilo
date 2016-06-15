package cat.raig.kikilo.infrastructure;


import org.junit.Test;

public class YouTubeRepositoryShould {

  @Test
  public void sampleUsage() {
    YouTubeRepository youTubeRepository = new YouTubeRepository();
    youTubeRepository.findByKeyWords("rojo");
  }

}

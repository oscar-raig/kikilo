package cat.raig.kikilo.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Video {
  @Id
  @GeneratedValue
  private Long id;
  String title;

  public void setId(Long id) {
    this.id = id;
  }

  String url;

  public Video(String title, String url) {
    this.title = title;
    this.url = url;

  }

  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public Long getId() {
    return id;
  }
}

package cat.raig.kikilo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Video {
  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String url;

  public void setId(Long id) {
    this.id = id;
  }



  public Video(String title, String url, Long id) {
    this.title = title;
    this.url = url;
    this.id = id;
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

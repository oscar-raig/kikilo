package cat.raig.kikilo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Video {
  @Id
  @GeneratedValue
  private Long id;
  private String title;
  private String url;
  @ManyToOne
  private User owner;

  public void setId(Long id) {
    this.id = id;
  }

  public Video() {

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

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}

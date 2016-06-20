package cat.raig.kikilo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Video {

  private String title;
  @Id
  private String id;
  @ManyToOne
  private User owner;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }


  public Video() {

  }

  public Video(String title, String id) {
    this.title = title;
    this.id = id;
  }

  public String getTitle() {
    return title;
  }




  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}

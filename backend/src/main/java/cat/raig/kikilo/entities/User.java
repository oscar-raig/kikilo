package cat.raig.kikilo.entities;


import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue
  private Long id;
  @Column(unique = true)
  private String username;
  private String password;
  private String email;
  @ElementCollection
  private List<String> roles;

  public User(String username, String password, String email, List<String> roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
    this.email = email;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }
}

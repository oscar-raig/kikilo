package cat.raig.kikilo.entities;

import cat.raig.kikilo.entities.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserShould {

  @Test
  public void getId() throws Exception {
    List<String> permission = Arrays.asList("ADD", "EDIT");
    User user = new User("a username", "a password", "an email", permission);

    assertThat(user.getId(), is(nullValue()));

  }

  @Test
  public void setId() throws Exception {

  }

  @Test
  public void getUsername() throws Exception {

  }

  @Test
  public void setUsername() throws Exception {

  }

  @Test
  public void getPassword() throws Exception {

  }

  @Test
  public void setPassword() throws Exception {

  }

  @Test
  public void getRoles() throws Exception {

  }
}

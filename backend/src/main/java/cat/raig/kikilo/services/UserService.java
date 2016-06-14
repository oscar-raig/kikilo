package cat.raig.kikilo.services;

import cat.raig.kikilo.repository.UserRepository;
import cat.raig.kikilo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean isUserRole(Long id, String role) {
    User user = userRepository.findOne(id);
    if (user == null) {
      return false;
    }

    List<String> roles = user.getRoles();
    return roles.stream().anyMatch(ownRole -> ownRole.equalsIgnoreCase(role));
  }

  public User getUser(Long id) {
    return userRepository.findOne(id);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}

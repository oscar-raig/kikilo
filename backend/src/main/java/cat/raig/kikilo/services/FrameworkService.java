package cat.raig.kikilo.services;

import cat.raig.kikilo.entities.Framework;
import cat.raig.kikilo.repository.FrameworkRepository;
import cat.raig.kikilo.security.UserSession;
import cat.raig.kikilo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FrameworkService {
  private UserService userService;
  private FrameworkRepository frameworkRepository;
  private UserSession userSession;

  @Autowired
  public FrameworkService(UserService userService,
                          FrameworkRepository frameworkRepository, UserSession userSession) {
    this.userService = userService;
    this.frameworkRepository = frameworkRepository;
    this.userSession = userSession;
  }

  public Framework addFramework(Long owner, String name, Long love) {
    User user = userService.getUser(owner);
    if (user != null) {
      List<String> roles = user.getRoles();
      if (roles.stream().anyMatch(p -> p.equalsIgnoreCase("ADD"))) {
        Framework fw = new Framework();
        fw.setLove(love);
        fw.setName(name);
        fw.setOwner(user);
        fw.setChangeData(new Date());

        return frameworkRepository.save(fw);
      }
    }

    return null;
  }

  public Framework updateFramework(Long owner, Long id, String name, Long valoration) {
    User user = userService.getUser(owner);
    if (user != null) {
      List<String> roles = user.getRoles();
      if (roles.stream().anyMatch(p -> p.equalsIgnoreCase("EDIT"))) {
        Framework fw = frameworkRepository.findOne(id);
        if (fw != null) {
          if (fw.getOwner().getId().equals(owner)) {
            fw.setName(name);
            fw.setLove(valoration);
            fw.setChangeData(new Date());

            return frameworkRepository.save(fw);
          }
        }
      }
    }

    return null;
  }

  public List<Framework> getMyFrameworks() {
    if (userSession == null) {
      return new ArrayList<>(0);
    }

    User user = userService.getUser(userSession.getId());
    if (user == null) {
      return new ArrayList<>(0);
    }

    List<String> roles = user.getRoles();
    if (roles.stream().anyMatch(p -> p.equalsIgnoreCase("VIEW"))) {
      return frameworkRepository.findByOwner(user);
    }

    return new ArrayList<>(0);
  }

  public Framework getFramework(Long id) {
    return frameworkRepository.findOne(id);
  }
}

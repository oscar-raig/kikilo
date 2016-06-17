package cat.raig.kikilo.services;

import cat.raig.kikilo.entities.User;
import cat.raig.kikilo.entities.Video;
import cat.raig.kikilo.repository.VideoRepository;
import cat.raig.kikilo.repository.YouTubeRepository;
import cat.raig.kikilo.security.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

  private   VideoRepository videoRepository;
  private   UserSession userSession;
  private   UserService userService;
  private   YouTubeRepository youTubeRepository;

  @Autowired
  public VideoService(VideoRepository videoRepository, UserSession userSession,
                      UserService userService, YouTubeRepository youTubeRepository) {
    this.videoRepository  = videoRepository;
    this.userSession = userSession;
    this.userService = userService;
    this.youTubeRepository = youTubeRepository;
  }

  public  List<Video> getMyFrameworks() {
    if (userSession == null) {
      return new ArrayList<>(0);
    }

    User user = userService.getUser(userSession.getId());
    if (user == null) {
      return new ArrayList<>(0);
    }

    List<String> roles = user.getRoles();
    if (roles.stream().anyMatch(p -> p.equalsIgnoreCase("VIEW"))) {
      return youTubeRepository.findByKeyWords("rojo");
    }

    return new ArrayList<>(0);
  }
}
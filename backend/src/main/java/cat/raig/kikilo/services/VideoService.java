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

  public  List<Video> getYouTubeVideoList()  {

    User user = getUser();

    List<String> roles = user.getRoles();
    if (roles.stream().anyMatch(p -> p.equalsIgnoreCase("VIEW"))) {
      return youTubeRepository.findByKeyWords("rojo");
    }

    return new ArrayList<>(0);
  }

  public List<Video> getVideoList() {
    User user = getUser();
    return videoRepository.findByOwner(user);
  }

  public Video getYouTubeVideo(String id) {
    return youTubeRepository.getVideoById(id);
  }

  private User getUser() {
    if (userSession == null) {
      throw new ForbiddenVideoService();
    }

    User user = userService.getUser(userSession.getId());
    if (user == null) {
      throw new ForbiddenVideoService();
    }
    return user;
  }

  public Video getVideo(Long videoId) {
    User user = getUser();
    return videoRepository.findByid(videoId);
  }

  public void addVideo(Video video) {
    User user = getUser();
    video.setOwner(user);
    videoRepository.save(video);
  }

  public void updateVideo(Video video) {
    User user = getUser();
    videoRepository.save(video);
  }


  public class ForbiddenVideoService extends RuntimeException {
  }
}

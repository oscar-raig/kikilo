package cat.raig.kikilo.services;


import cat.raig.kikilo.entities.User;
import cat.raig.kikilo.entities.Video;
import cat.raig.kikilo.repository.VideoRepository;
import cat.raig.kikilo.repository.YouTubeRepository;
import cat.raig.kikilo.security.UserSession;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class VideoServiceShould {

  private VideoRepository videoRepository;
  private UserSession userSession;
  private UserService userService;
  private YouTubeRepository youTubeRepository;
  private VideoService videoService;

  @Before
  public void setUp() {
    videoRepository = Mockito.mock(VideoRepository.class);
    userSession = Mockito.mock(UserSession.class);
    userService = Mockito.mock(UserService.class);
    youTubeRepository = Mockito.mock(YouTubeRepository.class);
    videoService = new VideoService(
            videoRepository,userSession, userService, youTubeRepository);
  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getMyVideosreturnExceptionWhenUserSessionIsNull() {

    VideoService videoService = new VideoService(
            videoRepository,null, userService, youTubeRepository);;
    videoService.getMyVideos();

  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getMyVideosShouldReturnExceptionWhenUserSessionReturnsNull() {

    when(userService.getUser(any())).thenReturn(null);
    videoService.getMyVideos();
  }

  @Test
  public void getMyVideosShouldReturnAListOfVideos() {
    List<Video> expectedListOfVideos = new ArrayList(){
      {
        add(new Video("a title","a url", new Long(1)));
      }
    };

    List<String> roles = Arrays.asList("View");
    when(userService.getUser(any())).thenReturn(new User("a username","a password ",
            "a email",roles));
    when(youTubeRepository.findByKeyWords(any())).thenReturn(expectedListOfVideos);
    List<Video> listOfVidoes = videoService.getMyVideos();

    assertThat(listOfVidoes.size(),is(expectedListOfVideos.size()));
  }

}
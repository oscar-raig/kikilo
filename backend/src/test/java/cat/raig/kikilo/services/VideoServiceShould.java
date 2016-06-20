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
import static org.mockito.Mockito.verify;
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
            videoRepository, userSession, userService, youTubeRepository);
  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getMyVideosreturnExceptionWhenUserSessionIsNull() {

    VideoService videoService = new VideoService(
            videoRepository, null, userService, youTubeRepository);;
    videoService.getYouTubeVideoList();

  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getMyVideosShouldReturnExceptionWhenUserSessionReturnsNull() {

    when(userService.getUser(any())).thenReturn(null);
    videoService.getYouTubeVideoList();
  }

  @Test
  public void getMyVideosShouldReturnAListOfVideos() {
    List<Video> expectedListOfVideos = new ArrayList(){
      {
        add(new Video("a title", "a url"));
      }
    };

    List<String> roles = Arrays.asList("View");
    when(userService.getUser(any())).thenReturn(new User("a username", "a password ",
            "a email", roles));
    when(youTubeRepository.findByKeyWords(any())).thenReturn(expectedListOfVideos);

    List<Video> listOfVidoes = videoService.getYouTubeVideoList();

    assertThat(listOfVidoes.size(), is(expectedListOfVideos.size()));
  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getVideoShouldReturnExceptionWhenUserSessionIsNull() {
    VideoService videoService = new VideoService(
            videoRepository, null, userService, youTubeRepository);
    Long videoId = new Long(1);
    videoService.getVideo(videoId);

  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void getVideoShouldReturnExceptionWhenUserSessionReturnsNull() {

    when(userService.getUser(any())).thenReturn(null);
    Long videoId = new Long(1);
    videoService.getVideo(videoId);
  }

  @Test
  public void getVideoShouldReturnAVideo() {

    List<String> roles = Arrays.asList("View");
    when(userService.getUser(any())).thenReturn(new User("a username", "a password ",
            "a email", roles));

    Long videoId = new Long(1);
    videoService.getVideo(videoId);

    verify(videoRepository).findByid(videoId);
  }


  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void addVideoShouldReturnExceptionWhenUserSessionIsNull() {
    VideoService videoService = new VideoService(
            videoRepository, null, userService, youTubeRepository);
    Video video = new Video("a video title", " a url ");
    videoService.addVideo(video);

  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void addVideoShouldReturnExceptionWhenUserSessionReturnsNull() {

    when(userService.getUser(any())).thenReturn(null);
    Video video = new Video("a video title", " a url ");
    videoService.addVideo(video);
  }

  @Test
  public void addVideoShouldSaveAVideo() {

    List<String> roles = Arrays.asList("View");
    when(userService.getUser(any())).thenReturn(new User("a username", "a password ",
            "a email", roles));
    Video video = new Video("a video title", " a url ");
    videoService.addVideo(video);

    verify(videoRepository).save(video);
  }



  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void updateVeideoShouldReturnExceptionWhenUserSessionIsNull() {
    VideoService videoService = new VideoService(
            videoRepository, null, userService, youTubeRepository);
    Video video = new Video("a video title", " a url ");
    videoService.updateVideo(video);

  }

  @Test(expected = VideoService.ForbiddenVideoService.class)
  public void updateVideoShouldReturnExceptionWhenUserSessionReturnsNull() {

    when(userService.getUser(any())).thenReturn(null);
    Video video = new Video("a video title", " a url ");
    videoService.updateVideo(video);
  }

  @Test
  public void updateVideoShouldSaveAVideo() {

    List<String> roles = Arrays.asList("View");
    when(userService.getUser(any())).thenReturn(new User("a username", "a password ",
            "a email", roles));
    Video video = new Video("a video title", " a url ");
    videoService.updateVideo(video);

    verify(videoRepository).save(video);
  }


}

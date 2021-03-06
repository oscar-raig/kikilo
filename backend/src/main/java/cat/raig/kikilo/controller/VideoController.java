package cat.raig.kikilo.controller;

import cat.raig.kikilo.controller.request.SaveFrameworkRequest;
import cat.raig.kikilo.entities.Video;
import cat.raig.kikilo.security.UserSession;
import cat.raig.kikilo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class VideoController {

  private static Logger LOGGER = LoggerFactory.getLogger(VideoController.class);

  private VideoService videoService;
  private UserSession userSession;

  @Autowired
  public VideoController(VideoService videoService, UserSession userSession) {
    this.videoService = videoService;
    this.userSession = userSession;
  }

  @RequestMapping("/video/list")
  public String viewVideoList(ModelMap model) {
    List<Video> myVideos;
    try {
      myVideos = videoService.getVideoList();
    } catch (VideoService.ForbiddenVideoService exception) {
      LOGGER.error("Error getting session");
      return "video/error";
    }
    model.put("videos", myVideos);

    return "video/list";
  }

  @RequestMapping("/youtube/list")
  public String viewYouTubeList(ModelMap model) {
    List<Video> myVideos;
    try {
      myVideos = videoService.getYouTubeVideoList();
    } catch (VideoService.ForbiddenVideoService exception) {
      LOGGER.error("Error getting session");
      return "video/error";
    }
    model.put("videos", myVideos);

    return "video/list";
  }


  @RequestMapping("/video/create/{id}")
  public String createVideo(@PathVariable String id, ModelMap model) {
    Video video = videoService.getYouTubeVideo(id);

    videoService.addVideo(video);
    List<Video> videos = videoService.getVideoList();
    model.put("videos", videos);
    return "video/list";
  }

  @RequestMapping(value = "/video", method = POST)
  public String saveVideo(@ModelAttribute SaveFrameworkRequest request, ModelMap model) {
    List<Video> listOfVideos = videoService.getYouTubeVideoList();
    model.put("videos", listOfVideos);
/*    Long videoId = new Long(1);
    Video video = request.getId() != null ? videoService.getVideo(videoId) : null;
    if (video == null) {
      videoService.addVideo( video);
    } else {
      videoService.updateVideo(video);
    }
*/
    return "redirect:/video/list";
  }
}

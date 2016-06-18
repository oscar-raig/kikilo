package cat.raig.kikilo.controller;

import cat.raig.kikilo.controller.request.SaveFrameworkRequest;
import cat.raig.kikilo.entities.Video;
import cat.raig.kikilo.security.UserSession;
import cat.raig.kikilo.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
      myVideos = videoService.getMyVideos();
    } catch (VideoService.ForbiddenVideoService exception) {
      LOGGER.error("Error getting session");
      return "video/error";
    }
    model.put("videos", myVideos);

    return "video/list";
  }


  @RequestMapping("/video/create")
  public String createVideo() {
    return "video/form";
  }

  @RequestMapping(value = "/video", method = POST)
  public String saveVideo(@ModelAttribute SaveFrameworkRequest request,ModelMap model) {
    List<Video> listOfVideos = videoService.getMyVideos();
    model.put("videos", listOfVideos);
    Video video = request.getId() != null ? videoService.getVideo() : null;
    if (video == null) {
      videoService.addVideos(userSession.getId(), request.getName(), request.getLove());
    } else {
      videoService.updateVideo(userSession.getId(), request.getId(),
              request.getName(), request.getLove());
    }

    return "redirect:/video/list";
  }
}

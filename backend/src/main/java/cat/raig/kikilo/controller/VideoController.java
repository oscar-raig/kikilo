package cat.raig.kikilo.controller;

import cat.raig.kikilo.entities.Framework;
import cat.raig.kikilo.security.UserSession;
import cat.raig.kikilo.services.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VideoController {

  private FrameworkService frameworkService;

  @Autowired
  public VideoController(FrameworkService frameworkService, UserSession userSession) {
    this.frameworkService = frameworkService;
  }

  @RequestMapping("/video/list")
  public String viewVideoList(ModelMap model) {
    List<Framework> myFrameworks = frameworkService.getMyFrameworks();
    model.put("frameworks", myFrameworks);

    return "video/list";
  }


  @RequestMapping("/video/create")
  public String createVideo() {
    return "video/form";
  }
}

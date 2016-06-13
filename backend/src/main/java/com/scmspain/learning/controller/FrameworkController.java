package com.scmspain.learning.controller;

import com.scmspain.learning.controller.request.SaveFrameworkRequest;
import com.scmspain.learning.entities.Framework;
import com.scmspain.learning.security.UserSession;
import com.scmspain.learning.services.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FrameworkController {
    private FrameworkService frameworkService;
    private UserSession userSession;

    @Autowired
    public FrameworkController(FrameworkService frameworkService, UserSession userSession) {
        this.frameworkService = frameworkService;
        this.userSession = userSession;
    }

    @RequestMapping("/framework/list")
    public String viewFrameworkList(ModelMap model) {
        List<Framework> myFrameworks = frameworkService.getMyFrameworks();
        model.put("frameworks", myFrameworks);

        return "framework/list";
    }

    @RequestMapping("/framework/create")
    public String createFramework() {
        return "framework/form";
    }

    @RequestMapping("/framework/edit/{id}")
    public String editFramework(@PathVariable Long id, ModelMap model) {
        Framework fw = frameworkService.getFramework(id);
        if (fw == null) {
            return "redirect:/framework/create";
        }

        model.put("framework", fw);
        return "framework/form";
    }

    @RequestMapping(value = "/framework", method = POST)
    public String saveFramework(@ModelAttribute SaveFrameworkRequest request) {
        Framework fw = request.getId() != null ? frameworkService.getFramework(request.getId()) : null;
        if (fw == null) {
            frameworkService.addFramework(userSession.getId(), request.getName(), request.getLove());
        } else {
            frameworkService.updateFramework(userSession.getId(), request.getId(), request.getName(), request.getLove());
        }

        return "redirect:/framework/list";
    }
}

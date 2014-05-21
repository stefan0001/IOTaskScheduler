package de.sep.innovativeoperation.taskscheduler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class DefaultController {
	
	
    @RequestMapping(method = RequestMethod.GET)
    public String getDefault() {
        return "redirect:/static/gui/index.html";
    }
}

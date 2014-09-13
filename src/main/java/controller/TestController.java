package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    
    @RequestMapping("/test")
    public String test(ModelMap model) {
        model.addAttribute("name", "this is some text I entered");
        return "test";
    }
}

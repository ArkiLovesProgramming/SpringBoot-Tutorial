package arki.springboot.sb07security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RouterController {
    @RequestMapping({"/", "/welcome"})
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/level/level{num}")
    public String level(@PathVariable String num){
        return "level/level" + num;
    }
}

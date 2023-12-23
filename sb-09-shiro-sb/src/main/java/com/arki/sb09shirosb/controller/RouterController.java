package com.arki.sb09shirosb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @RequestMapping({"/", "/welcome"})
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/login")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/level/level{num}")
    public String level(@PathVariable String num){
        return "level/level" + num;
    }
}

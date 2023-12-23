package arki.springboot.sb03thymeleaf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class TestController {

    @RequestMapping("/t1")
    public String test1(Model model){
        model.addAttribute("msg", "hello!");
        return "test";
    }

    @RequestMapping("/t2")
    public String test2(HashMap<String, Object> map){
        map.put("msg", "<h1>这是标题1！！！</h1>");
        map.put("users", Arrays.asList("weiweix", "alisha"));
        return "test";
    }
}

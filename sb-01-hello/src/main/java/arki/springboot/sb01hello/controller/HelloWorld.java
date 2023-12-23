package arki.springboot.sb01hello.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello!";
    }
}

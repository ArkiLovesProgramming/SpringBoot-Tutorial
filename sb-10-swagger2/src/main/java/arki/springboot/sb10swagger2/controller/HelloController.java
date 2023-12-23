package arki.springboot.sb10swagger2.controller;

import arki.springboot.sb10swagger2.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("类名上")
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String helloController(){
        return "hello";
    }

    @ApiOperation("方法接口上")
    @RequestMapping("/user")
    public User user(){
        return new User("ww", 123);
    }

    @ApiOperation("方法接口上1")
    @RequestMapping("/user/{username}")
    public User user1(@PathVariable String username){
        return new User(username, 123);
    }
}

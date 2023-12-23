package arki.springboot.sb11asyncsyncemail.controller;

import arki.springboot.sb11asyncsyncemail.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/invoke")
    public String invoke() throws ExecutionException, InterruptedException {
        Future data = asyncService.process();
        return data.get() + " result is here";
    }
}

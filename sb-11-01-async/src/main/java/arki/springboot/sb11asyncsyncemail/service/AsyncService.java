package arki.springboot.sb11asyncsyncemail.service;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.concurrent.Future;

@Service
public class AsyncService {
    @Async
    public Future<String> process(){
        try {
            System.out.println("Data is processing!");
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new AsyncResult<>("Hello, World");
    }
}

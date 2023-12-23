package arki.springboot.sb11asyncsyncemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Sb11AsyncSyncEmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sb11AsyncSyncEmailApplication.class, args);
    }

}

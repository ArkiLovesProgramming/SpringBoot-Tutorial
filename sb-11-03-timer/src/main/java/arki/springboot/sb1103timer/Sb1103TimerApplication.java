package arki.springboot.sb1103timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Sb1103TimerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb1103TimerApplication.class, args);
    }

}

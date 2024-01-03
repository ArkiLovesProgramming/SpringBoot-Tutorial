package arki.springboot.sb01hello;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//注意目录结构
@SpringBootApplication
public class Sb01HelloApplication {

    public static void main(String[] args) {
        ApplicationContext context = (ApplicationContext) SpringApplication.run(Sb01HelloApplication.class, args);
    }

}

package arki.springboot.config;

import arki.springboot.pojo.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

public class MyConfig {
    @Bean
    @ConfigurationProperties(prefix = "user")
    public User User(){
        return new User();
    }
}

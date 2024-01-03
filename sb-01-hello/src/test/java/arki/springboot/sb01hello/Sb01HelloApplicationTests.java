package arki.springboot.sb01hello;

import arki.springboot.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootTest
class Sb01HelloApplicationTests {

    @Autowired
    DispatcherServlet dispatcherServlet;

    @Test
    void contextLoads() {

    }

}

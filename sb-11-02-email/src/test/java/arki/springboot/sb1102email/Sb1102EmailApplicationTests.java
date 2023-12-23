package arki.springboot.sb1102email;

import arki.springboot.sb1102email.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Sb1102EmailApplicationTests {
    @Autowired
    private MailService mailService;

    @Test
    void contextLoads() {
        //mailService.sendSimpleEmail();
        mailService.sendComplexMail("1090409167@qq.com", "test as well!", "Test!", "/Users/beita/Desktop/ProfiePic.jpeg");
    }

}

package arki.springboot.sb06mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("arki.springboot.sb06mybatis.mapper")
public class Sb06MybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb06MybatisApplication.class, args);
	}

}

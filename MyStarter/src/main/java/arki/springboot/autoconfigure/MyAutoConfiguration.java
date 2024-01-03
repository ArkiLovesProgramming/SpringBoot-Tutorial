package arki.springboot.autoconfigure;

import arki.springboot.config.MyConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(MyConfig.class)
public class MyAutoConfiguration {
}

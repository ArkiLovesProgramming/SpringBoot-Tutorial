package arki.springboot.sb02yaml.pojo;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/*
@ConfigurationProperties作用：
将配置文件中配置的每一个属性的值，映射到这个组件中；
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix = “dog” : 将配置文件中的person下面的所有属性一一对应
底层是通过set方法实现

它的意思是“Spring Boot配置注解执行器没有配置”，配置注解执行器的好处是什么。
配置注解执行器配置完成后，当执行类中已经定义了对象和该对象的字段后，在配置文件中对该类赋值时，便会非常方便的弹出提示信息。
*/
@Data
@Component
@ConfigurationProperties(prefix = "dog")
public class Dog {
    private String myName;
    private int age;
    private String email;
}

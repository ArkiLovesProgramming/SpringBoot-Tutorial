# Spring Boot

Spring Boot 是 Spring 框架的一个扩展，用于简化基于 Spring 框架的应用程序的开发。Convention over configuration。

**约定大于配置：以前我们要导入依赖，写大量的 xml以及手动注册 bean，现在 springboot 提供大量默认配置，我们只需要修改我们需要的配置既可，springboot帮我们进行自动装配，包括导入依赖集合，基础配置以及注册 bean**

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

狂神笔记：https://kuriyama.net.cn/blog/91

http://c.biancheng.net/spring_boot/servlet-filter-listener.html

学习笔记：https://blog.csdn.net/weixin_52798101/article/details/126532226?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1-126532226-blog-124384771.235%5Ev38%5Epc_relevant_sort&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-1-126532226-blog-124384771.235%5Ev38%5Epc_relevant_sort&utm_relevant_index=2

## 前情提要

微服务阶段

+ javase:AOP
+ mysql:持久化
+ html+css+js+jquery-+框架：视图，框架不熟练，css不好；
+ javaweb:独立开发MVC三层架构的网站了：原始
+ ssm：框架：简化了我们的开发流程
+ war:tomcati运行
+ spring再简化：SpringBoot;微服务架构！
+ 服务越来越多：springcloud

![在这里插入图片描述](assets/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBALUJsdWUu,size_20,color_FFFFFF,t_70,g_se,x_16-7102494.png)

## 一、什么是SpringBoot？什么是微服务？

SpringBoot01：Hello,World！

### 环境准备

+ jdk1.8
+ maven 3.6.1
+ springboot:最新
+ IDEA

### 创建项目

官方：提供了一个快速生成的网站！IDEA集成了这个网站！
两种方法：

+ 可以在官网直接下载后，导入idea开发[SpringBoot官网下载项目jar包](https://start.spring.io/)
+ 直接使用idea创建一个springbootI项目（一般开发直接在IDEA中创建）

### 小玩意儿

1. 改端口号

   在applicationl.properties加入

   server.port=8081

2. 改banner

   在resource目录下加入banner.text，编辑banner

   <img src="assets/image-20230619120739385.png" style="zoom:50%;" />

### 项目一般框架

<img src="assets/image-20230629132725737.png" alt="image-20230629132725737" style="zoom:50%;" />

## 二、原理初探（学完再来看）

原文链接

https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483743&idx=1&sn=431a5acfb0e5d6898d59c6a4cb6389e7&scene=19#wechat_redirect

## 三、yaml语法

原文连接

https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483744&idx=1&sn=b4ec762e71b2ddf9403c035635299206&scene=19#wechat_redirect

<img src="assets/image-20230619122038735.png" alt="image-20230619122038735" style="zoom:50%;" />

+ springboot这个配置文件中到底可以配置哪些东西呢？ 
+ 官方的配置太多了 
+ 了解原理：一通百通

<img src="assets/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBALUJsdWUu,size_20,color_FFFFFF,t_70,g_se,x_16-7186282.png" alt="在这里插入图片描述" style="zoom: 67%;" />

###  yaml概述

- YAML是 “YAML Ain’t a Markup Language” （YAML不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是：“Yet Another Markup Language”（仍是一种标记语言）
- 这种语言以数据作为中心，而不是以标记语言为重点！
- 以前的配置文件，大多数都是使用`xml`来配置；比如一个简单的端口配置，我们来对比下`yaml`和`xml`

1. 传统xml配置：

   ```xml
   <server>
       <port>8081<port>
   </server>
   ```

2. yaml配置:

   ```yaml
   server：
     prot: 8080
   ```

### 语法

- SpringBoot使用一个全局的配置文件 ， 配置文件名称是固定的

1.  `application.properties` 语法结构 ：`key=value` 
2.  `application.yml` 语法结构 ：`key：空格 value` 

-  配置文件的作用 ：修改SpringBoot自动配置的默认值，因为SpringBoot在底层都给我们自动配置好了； 

-  比如我们可以在配置文件中修改Tomcat 默认启动的端口号

  ```properties
  server.port=8081
  ```

![在这里插入图片描述](assets/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBALUJsdWUu,size_20,color_FFFFFF,t_70,g_se,x_16-20230619122210175.png)

### 给属性赋值的三种方式

#### 1、@Value

dog类

```java
@Component
public class Dog {
    @Value("capy")
    private String name;
    @Value("1")
    private int age;
}
```

#### 2、yaml

本质是通过set方法注入的

application.yaml

```yaml
dog:
  name: capybara
  age: 1
```

加上配置依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

为了防止warning

<img src="assets/image-20230619124813526.png" alt="image-20230619124813526" style="zoom:50%;" />

它的意思是“Spring Boot配置注解执行器没有配置”，配置注解执行器的好处是什么。
配置注解执行器配置完成后，当执行类中已经定义了对象和该对象的字段后，**在配置文件中对该类赋值时，便会非常方便的弹出提示信息**。 

Dog类

```java
/*
@ConfigurationProperties作用：
将配置文件中配置的每一个属性的值，映射到这个组件中；
告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
参数 prefix = “dog” : 将配置文件中的person下面的所有属性一一对应
底层是通过set方法实现
*/
@Component
@ConfigurationProperties(prefix = "dog")
public class Dog {
    private String name;
    private int age;
//set, get....
}
```

关于**@ConfigurationPropertie**s，注意：该注解可以用在方法上或者类上，方法上的例子如下：

用于配置类

```java
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }
}
```

##### 附加：

松散绑定：-命名到驼峰命名也能绑定

```yaml
dog:
  my-name: capybara
  age: 1
```

```java
public class Dog {
    private String myName;
    private int age;
}
```

##### 后期学习补充

如果待赋值的类中有 hashmap 类型的值，并且定义了比如

```
this.serviceUrl.put("defaultZone", "http://localhost:8761/eureka/");
```

则我们可以用如下赋值

![image-20231013150941702](assets/image-20231013150941702.png)

##### @value

也支持下面将要介绍的@Value(${})获取

#### 3、properties

```properties
dog.name=capybara2
dog.age=3
```

![image-20230619125247950](assets/image-20230619125247950.png)

```java
@Component
@PropertySource(value = "classpath:application.properties")
public class Dog {
    @Value("${dog.name}")	//这种方法也使用 yml 的配置文件！！！！所以 yml 的 ConfigurationProperties才是 yml 特有的方式
    private String name;
    @Value("${dog.age}")
    private int age;
}
```

测试

```java
@SpringBootTest
class Sb02YamlApplicationTests {
    @Autowired
    private Dog dog;

    @Test
    void contextLoads() {
        System.out.println(dog);
    }
}
```

#### 结论：

+ 配置yml和配置properties都可以获取到值 ， **强烈推荐 yml**；
+ 如果我们在某个业务中，只需要获取配置文件中的某个值，可以使用一下 @value；
+ 如果说，我们专门编写了一个JavaBean来和配置文件进行一一映射，就直接用yaml，@configurationProperties，不要犹豫！

#### yaml和@value对比

![image-20230619130759281](assets/image-20230619130759281.png)

### [SR303](https://so.csdn.net/so/search?q=JSR303&spm=1001.2101.3001.7020)数据校验

使用@Validated去校验configurationproperties里面配置文件是否符合标准

![在这里插入图片描述](assets/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBALUJsdWUu,size_20,color_FFFFFF,t_70,g_se,x_16-20230620111650684.png)

**使用数据校验，可以保证数据的正确性；**

#### 常见参数

```
@NotNull(message="名字不能为空")
private String userName;
@Max(value=120,message="年龄最大不能查过120")
private int age;
@Email(message="邮箱格式错误")
private String email;

空检查
@Null       验证对象是否为null
@NotNull    验证对象是否不为null, 无法查检长度为0的字符串
@NotBlank   检查约束字符串是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
@NotEmpty   检查约束元素是否为NULL或者是EMPTY.
    
Booelan检查
@AssertTrue     验证 Boolean 对象是否为 true  
@AssertFalse    验证 Boolean 对象是否为 false  
    
长度检查
@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内  
@Length(min=, max=) string is between min and max included.

日期检查
@Past       验证 Date 和 Calendar 对象是否在当前时间之前  
@Future     验证 Date 和 Calendar 对象是否在当前时间之后  
@Pattern    验证 String 对象是否符合正则表达式的规则

.......等等
除此以外，我们还可以自定义一些数据校验规则
```

### 多环境配置

#### 配置文件优先级

config>项目目录>resources/config>resouces

![在这里插入图片描述](assets/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBALUJsdWUu,size_20,color_FFFFFF,t_70,g_se,x_16-20230620111852863.png)

![image-20230922104555953](assets/image-20230922104555953.png)

#### properties实现

![在这里插入图片描述](assets/43b9505b6e934a1aab37a5ca6773b60e.png)

![在这里插入图片描述](assets/63fa474345904e11a107d4c63dcbba02.png)

#### yml实现

**application.yaml**

```yml
server:
  port: 8081
spring:
  profiles:
    active: dev
---
server:
  port: 8082
spring:
  profiles: dev

---
server:
  port: 8083
spring:
  profiles: test

```

#### 总结：

yaml实现多环境配置可以放在一个文件里，用---隔开，用properteis就得分好多文件（但是yml也可以分好多文件（**我觉得还是分开比较好**

## 四、自动配置原理再谈

**原文：**https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483766&idx=1&sn=27739c5103547320c505d28bec0a9517&scene=19#wechat_redirect

### 23.12.24再理解(重点)

这种方式其实是

自动装配就是 spring 自动把自身(如 mvc 的 dispatchservlet) 和第三方(如 mybatis)的 bean装配到 IOC 容器中，实现 bean 之间的互相注入，且并完成相关配置。@SpringBootApplication 注解下的@EnableAutoConfiguration，通过@Import(AutoConfigurationImportSelector.class), 注入了 ImportSelector 类的实现了，再深挖可以找到如下图：

![image-20231224205426868](assets/image-20231224205426868.png)

可知，springboot 或查询META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports下的文件，并把所有（包括第三方）找到的.import 文件下的类路径作为 String[]的形式返回给@EnableAutoConfiguration里面的@Import(...)，这样就实现了注入所有的 bean（自身，以及第三方）。

![image-20231224212512341](assets/image-20231224212512341.png)

**注意 XXXautoconfig类需要实现@AutoConfiguration 注解，然后META-INF/spring 下的类路径才会被识别**。

合并 1和2，去掉 2 的 import 其实也是可以的。

**如何通过@EnbaleXXX 的方式注入，而不走 springboot 的自动注入？**

只需要去掉第四步，这样 springboot 的 autoconfigure 就没法找到它的 import 类路径，就不会让 springboot 自动注入了。然后我们把 CommonAutoConfig 变成一个以 Enable 开头的注解，再 sb 主启动项上标记就行了。

### 原来的内容

官方文档有大量配置，很难记住

![Image](assets/640.jpeg)

### 精髓

1、SpringBoot启动会加载大量的自动配置类
2、我们看我们需要的功能有没有在SpringBoot默认写好的自动配置类当中；
3、我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件存在在其中，我们就不需要再手动配置了）
4、给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们只需要在配置文件中指定这些属性的值即可；

`xxxxAutoConfigurartion`：自动配置类；给容器中添加组件，连接配置文件类

`xxxxProperties`:封装配置文件中相关属性；

我们可以通过启用 `debug=true`属性；来让控制台打印自动配置报告，这样我们就可以很方便的知道哪些自动配置类生效；

```properties
#开启springboot的调试类
debug=true
```

### 了解：@Conditional

**用来判断 IOC 容器注入bean是否执行**

了解完自动装配的原理后，我们来关注一个细节问题，**自动配置类必须在一定的条件下才能生效；**

**@Conditional派生注解（Spring注解版原生的@Conditional作用）**

作用：必须是@Conditional指定的条件成立，**才给容器中添加组件**，配置配里面的所有内容才生效；

![Image](assets/640-20230620121010886.jpeg)

## 五、静态资源处理

### 静态资源映射规则

SpringBoot中，SpringMVC的web配置都在 **WebMvcAutoConfiguration** 这个配置类里面；

我们可以去看看 WebMvcAutoConfigurationAdapter 中有很多配置方法；

有一个方法：**addResourceHandlers** 添加资源处理

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!this.resourceProperties.isAddMappings()) {
        // 已禁用默认资源处理
        logger.debug("Default resource handling disabled");
        return;
    }
    // 缓存控制
    Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
    CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
    // webjars 配置
    if (!registry.hasMappingForPattern("/webjars/**")) {
        customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
                                             .addResourceLocations("classpath:/META-INF/resources/webjars/")
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
    // 静态资源配置
    String staticPathPattern = this.mvcProperties.getStaticPathPattern();
    if (!registry.hasMappingForPattern(staticPathPattern)) {
        customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
                                             .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
                                             .setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl));
    }
}
```

读一下源代码：比如所有的 /webjars/** ， 都需要去 classpath:/META -INF/resources/webjars/ 找对应的资源；

### 第一种方式：webjars

Webjars本质就是以jar包的方式引入我们的静态资源 ， 我们以前要导入一个静态资源文件，直接导入即可。

使用SpringBoot需要使用Webjars，我们可以去搜索一下：

网站：https://www.webjars.org

比如，要使用jQuery，我们只要要引入jQuery对应版本的pom依赖即可！

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.4.1</version>
</dependency>
```

导入完毕，查看webjars目录结构，并访问Jquery.js文件！

<img src="assets/20220307143503.png" alt="img" style="zoom:50%;" />

访问：只要是静态资源，SpringBoot就会去对应的路径寻找资源，我们这里访问：http://localhost:8080/webjars/jquery/3.4.1/jquery.js

![img](assets/20220307143522.png)

### 第二种静态资源映射规则

那我们项目中要是**使用自己的静态资源**该怎么导入呢？我们看下一行代码；

我们去找**staticPathPattern**发现第二种映射规则 ：/** , 访问当前的项目任意资源，它会去找 resourceProperties 这个类，我们可以点进去看一下分析

```java
// 进入方法
public String[] getStaticLocations() {
    return this.staticLocations;
}
// 找到对应的值
private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
// 找到路径
private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { 
    "classpath:/META-INF/resources/",
  "classpath:/resources/", 
    "classpath:/static/", 
    "classpath:/public/" 
};
```

ResourceProperties 可以设置和我们静态资源有关的参数；这里面指向了它会去寻找资源的文件夹，即上面数组的内容。

所以得出结论，以下四个目录存放的静态资源可以被我们识别：

```
"classpath:/META-INF/resources/"
"classpath:/resources/"
"classpath:/static/"
"classpath:/public/"
```

我们可以在resources根目录下新建对应的文件夹，都可以存放我们的静态文件；

比如我们访问 http://localhost:8080/1.js , 他就会去这些文件夹中寻找对应的静态资源文件；

### 自定义静态资源路径

我们也可以自己通过配置文件来指定一下，哪些文件夹是需要我们放静态资源文件的，在application.properties中配置；

```properties
spring.resources.static-locations=classpath:/coding/,classpath:/kuang/
```

一旦自己定义了静态文件夹的路径，原来的自动配置就都会失效了！

### 欢迎页面

![在这里插入图片描述](assets/93d2374ff03e49e3bc9ef0c8899e85cc.png)

添加`index`

![在这里插入图片描述](assets/9664321ab9354a1e9e1f7c5622e97efe.png)

### 图标

老版本有，新版不一定

## 六、Thymeleaf

学习地址：https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483807&idx=1&sn=7e1d5df51cdeb046eb37dec7701af47b&scene=19#wechat_redirect

参考学习地址：https://juejin.cn/post/6992858307065020453

### 模版引擎

由于springboot打包是~~war包而不再是jar包~~(你哪里学来的？？？springboot 打包是 jar 包，这是它的特性，因为tomcat 内嵌了，所以我们不需要 war 了，因为 war 只能执行在 tomcat 服务器下，当然我们也可以配置 springboot 让其打包 war)，其次Tomcat是内嵌的，所以它默认并不支持JSP了，springboot推荐使用模版引擎来控制页面。

什么是模版引擎？

模板引擎，我们其实大家听到很多，其实jsp就是一个模板引擎（因为它有很多标签表达式，包括 c 标签，el 表达式等等），还有用的比较多的freemarker，包括**SpringBoot给我们推荐的Thymeleaf**，模板引擎有非常多，但再多的模板引擎，他们的思想都是一样的，什么样一个思想呢我们来看一下这张图：

![Image](assets/640.png)

### 引入模版引擎

怎么引入呢，对于springboot来说，什么事情不都是一个start的事情嘛，我们去在项目中引入一下。给大家三个网址：

Thymeleaf 官网：https://www.thymeleaf.org/

Thymeleaf 在Github 的主页：https://github.com/thymeleaf/thymeleaf

Spring官方文档：找到我们对应的版本: https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-starter 

找到对应的pom依赖：可以适当点进源码看下本来的包！

```xml
<!--thymeleaf-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

Maven会自动下载jar包，我们可以去看下下载的东西；

![Image](assets/640-20230622233053811.png)

### Thymeleaf分析

前面呢，我们已经引入了Thymeleaf，那这个要怎么使用呢？

我们首先得按照SpringBoot的自动配置原理看一下我们这个Thymeleaf的自动配置规则，在按照那个规则，我们进行使用。

我们去找一下Thymeleaf的自动配置类：ThymeleafProperties

```java
@ConfigurationProperties(
    prefix = "spring.thymeleaf"
)
public class ThymeleafProperties {
    private static final Charset DEFAULT_ENCODING;
    public static final String DEFAULT_PREFIX = "classpath:/templates/";
    public static final String DEFAULT_SUFFIX = ".html";
    private boolean checkTemplate = true;
    private boolean checkTemplateLocation = true;
    private String prefix = "classpath:/templates/";
    private String suffix = ".html";
    private String mode = "HTML";
    private Charset encoding;
}
```

我们可以在其中看到默认的前缀和后缀！

我们只需要**把我们的html页面放在类路径下的templates下**，thymeleaf就可以帮我们自动渲染了。

使用thymeleaf什么都不需要配置，只需要将他放在指定的文件夹下即可！

### 应用

#### controller

thymeleaf可以通过**map**来向前端输送数据，这是区别于springMVC的点！

```java
@Controller
public class TestController {

    @RequestMapping("/t1")
    public String test1(Model model){
        model.addAttribute("msg", "hello!");
        return "test";
    }

    @RequestMapping("/t2")
    public String test2(HashMap<String, Object> map){
        map.put("msg", "<h1>这是标题1！！！</h1>");
        map.put("users", Arrays.asList("weiweix", "alisha"));
        return "test";
    }
}
```

#### html

需要给html加入命名空间！这样才有提示

```
xmlns:th="http://www.thymeleaf.org"
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>thymeleaf</title>
</head>
<body>
<!--th:text就是将div中的内容设置为它指定的值，和之前学习的Vue一样-->
<div th:text="${msg}"></div>
<div th:utext="${msg}"></div>
<h4 th:each="user : ${users}" th:text="${user}"></h4>
</body>
</html>
```

#### 语法

要学习语法，还是参考官网文档最为准确，我们找到对应的版本看一下；

Thymeleaf 官网：https://www.thymeleaf.org/ ， 简单看一下官网！我们去下载Thymeleaf的官方文档！

![Image](assets/640-20230622233557269.jpeg)

#### 表达式

```

Simple expressions:（表达式语法）
Variable Expressions: ${...}：获取变量值；OGNL；
    1）、获取对象的属性、调用方法
    2）、使用内置的基本对象：#18
         #ctx : the context object.
         #vars: the context variables.
         #locale : the context locale.
         #request : (only in Web Contexts) the HttpServletRequest object.
         #response : (only in Web Contexts) the HttpServletResponse object.
         #session : (only in Web Contexts) the HttpSession object.
         #servletContext : (only in Web Contexts) the ServletContext object.

    3）、内置的一些工具对象：
　　　　　　#execInfo : information about the template being processed.
　　　　　　#uris : methods for escaping parts of URLs/URIs
　　　　　　#conversions : methods for executing the configured conversion service (if any).
　　　　　　#dates : methods for java.util.Date objects: formatting, component extraction, etc.
　　　　　　#calendars : analogous to #dates , but for java.util.Calendar objects.
　　　　　　#numbers : methods for formatting numeric objects.
　　　　　　#strings : methods for String objects: contains, startsWith, prepending/appending, etc.
　　　　　　#objects : methods for objects in general.
　　　　　　#bools : methods for boolean evaluation.
　　　　　　#arrays : methods for arrays.
　　　　　　#lists : methods for lists.
　　　　　　#sets : methods for sets.
　　　　　　#maps : methods for maps.
　　　　　　#aggregates : methods for creating aggregates on arrays or collections.
==================================================================================

  Selection Variable Expressions: *{...}：选择表达式：和${}在功能上是一样；
  Message Expressions: #{...}：获取国际化内容
  Link URL Expressions: @{...}：定义URL；
  Fragment Expressions: ~{...}：片段引用表达式

Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
      
Text operations:（文本操作）
    String concatenation: +
    Literal substitutions: |The name is ${name}|
    
Arithmetic operations:（数学运算）
    Binary operators: + , - , * , / , %
    Minus sign (unary operator): -
    
Boolean operations:（布尔运算）
    Binary operators: and , or
    Boolean negation (unary operator): ! , not
    
Comparisons and equality:（比较运算）
    Comparators: > , < , >= , <= ( gt , lt , ge , le )
    Equality operators: == , != ( eq , ne )
    
Conditional operators:条件运算（三元运算符）
    If-then: (if) ? (then)
    If-then-else: (if) ? (then) : (else)
    Default: (value) ?: (defaultvalue)
    
Special tokens:
    No-Operation: _
```

#### 额外

开发时候把spring.thymeleaf.cache=false（默认是true）. 这是保证开放环境模版页面会实时随修改而更新。

## 拦截器


实现登录拦截器的步骤总结如下：

1. 创建一个拦截器类，实现`HandlerInterceptor`接口或继承`HandlerInterceptorAdapter`类。并且在拦截器中实现登录验证的逻辑，可以根据需求从请求中获取登录凭证进行验证。

   ```java
   public class LoginInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           // 登录验证逻辑
           if (用户未登录) {
               // 未登录处理逻辑，例如返回错误信息或重定向到登录页面
               return false;
           } else {
               // 已登录，继续处理请求
               return true;
           }
       }
   }
   ```

   ```java
   public class HelloInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           System.out.println("Pre Handle method is Calling");
           //必须返回 true 保证 interceptor 链式执行
           return true;
       }
   
       @Override
       public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
           System.out.println("Post Handle method is Calling");
       }
   
       @Override
       public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
           System.out.println("Request and Response is completed");
       }
   }
   ```

2. 在自定义的webconfig中注册拦截器并配置拦截路径，在Spring Boot的配置类中重写`addInterceptors`方法，将拦截器添加到拦截器链中，并指定需要拦截的路径。

   ```java
   @Configuration
   public class WebConfig implements WebMvcConfigurer {
       @Override
       public void addInterceptors(InterceptorRegistry registry) {
           registry.addInterceptor(new LoginInterceptor())
                   .addPathPatterns("/secured/**"); // 指定需要拦截的路径
       }
   }
   ```

3. 通过创建`WebConfig`类并实现`WebMvcConfigurer`接口，您可以覆盖默认的配置，添加自定义的配置，并对Web应用程序进行个性化定制。例如，您可以在`WebConfig`中添加**拦截器、配置静态资源访问、注册自定义的消息转换器**等。

## 七、国际化

https://mp.weixin.qq.com/s/e4Jd3xIMF4C4HBzPQfakvg

## 八、整合JDBC

### 步骤

1. 我去新建一个项目测试：springboot-data-jdbc ; 引入相应的模块！基础模块

   ![Image](assets/640-20230627204650740.jpeg)

2. 项目建好之后，发现自动帮我们导入了如下的启动器：

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-jdbc</artifactId>
   </dependency>
   <dependency>
       <groupId>com.mysql</groupId>
       <artifactId>mysql-connector-j</artifactId>
       <scope>runtime</scope>
   </dependency> 
   ```

3. 编写yaml配置文件连接数据库；

   为什么知道是这么写？参照springboot自动装配原理

   ```yml
   spring:
     datasource:
       driver-class-name: com.mysql.cj.jdbc.Driver
       username: root
       password: 12345678
       url: jdbc:mysql://localhost:3306/school?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
   ```

4. 配置完这一些东西后，我们就可以直接去使用了，因为SpringBoot已经默认帮我们进行了自动配置；去测试类测试一下

   ```java
   @SpringBootTest
   class Sb04JdbcApplicationTests {
   
       @Autowired
       DataSource dataSource;
   
       @Autowired
       JdbcTemplate jdbcTemplate;
   
       @Test
       void contextLoads() throws SQLException {
           //看一下默认数据源
           System.out.println(dataSource.getClass());
           //获得连接
           Connection connection =   dataSource.getConnection();//这段代码其实没有意义，JdbcTemplate已经从容器中获取了 datasource，并自动完成链接和断联的操作
           String sql = "select * from student where StudentNo = ?";
           Object[] args = new Object[1];
           args[0] = 1000;
           List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, args);
           System.out.println(maps);
           //关闭连接
           connection.close();
       }
   
   }
   ```

   结果：我们可以看到他默认给我们配置的数据源为 : class com.zaxxer.hikari.HikariDataSource ， 我们并没有手动配置。在spring2.5之后默认使用hikari

   当然，**我们可以使用 spring.datasource.type 指定自定义的数据源类型，值为 要使用的连接池实现的完全限定名。**

### JDBCTemplate

1、有了数据源(com.zaxxer.hikari.HikariDataSource)，然后可以拿到数据库连接(java.sql.Connection)，有了连接，就可以使用原生的 JDBC 语句来操作数据库；

2、即使不使用第三方第数据库操作框架，如 MyBatis等，Spring 本身也对原生的JDBC 做了轻量级的封装，即JdbcTemplate。

3、数据库操作的所有 CRUD 方法都在 JdbcTemplate 中。

4、Spring Boot 不仅提供了默认的数据源，同时默认已经配置好了 JdbcTemplate 放在了容器中，程序员只需自己注入即可使用

5、JdbcTemplate 的自动配置是依赖 org.springframework.boot.autoconfigure.jdbc 包下的 JdbcTemplateConfiguration 类

**JdbcTemplate主要提供以下几类方法：**

- execute方法：可以用于执行任何SQL语句，一般用于**执行DDL语句**；
- update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
- query方法及queryForXXX方法：用于执行查询相关语句；
- call方法：用于执行存储过程、函数相关语句。

## 九、整合Druid（解决）

阿里巴巴的Druid是一款高性能的数据库连接池和SQL查询优化工具。它可以帮助提高数据库访问的性能，同时提供实时监控和SQL注入防护功能。在Java开发中广泛应用，适用于高并发的企业应用。

http://c.biancheng.net/spring_boot/druid.html

狂神是用自己添加 datasource 进容器的https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483786&idx=1&sn=f5f4ca792611af105140752eb67ce820&scene=19#wechat_redirect

druid 引入了 starter 了，就可以不用自己把 datasource 放入容器了：https://segmentfault.com/a/1190000039005979

**一直卡在配置 Druid 监控管理后台的Servlet，创建ServletRegistrationBean一直报错，显示statservlet不满足参数类型要求，推测是版本问题。------------------问题解决9.22/23！原因是因为 springboot3 的 bug，这里解释的很清楚了https://github.com/alibaba/druid/issues/5022。好像是因为SpringBoot切换了一些版本依赖，将**javax**切换到了**jakarta****

## 十、整合myBatis（解决）

**一直卡在加了@mapper和@repository的mapper接口还是没法被controller自动注入扫描到。**推测是版本问题。

解决：不知道是什么问题，但是其实不需要加和@repository，因为它本来就是接口（不能注册成 bean），~~如果没有@Mapper，它是不能被创建成 bean 的~~，@Mapper 只是用了反射，实现了该接口并注册成 bean，也可以在启动类上添加@MapperScan("...")，这样就不用在每个接口上添加@Mapper 了。

注意：

1. **springboot3 的版本好像有点问题，不支持yml，所以建议用 properties 吧**
2. Mapper.xml放在 resources 下面的 mappers 文件夹，记住要加入静态资源过滤，然后 application 中配置mybatis.mapper-locations=classpath*:**/mappers/*Mapper.xml
3. 不知道是什么问题，但是其实不需要加和@repository，因为它本来就是接口，如果没有@Mapper，它是不能被创建成 bean 的，也可以在启动类上添加@MapperScan("...")，这样就不用在每个接口上添加@Mapper 了。

**个人理解：**

springboot 帮忙创建了 sqlSessionFactory 根据已经存在在容器的 dataSource，然后由于 mybatis-starter的加入，我们可以将配置直接写在 application 中，当然你也可以加"mybatis.config-location="去添加你的配置文件（不过你需要的其实都已经写入了）。根据mybatis.mapper-locations，扫描所以的 mapper.xml，xml 又去链接接口，接口被注入容器，容器又有 sqlsessionfactory 和 mybatis 配置，所以一切都连起来了。然后直接从容器里面拿 mapper 来用就好了，**springboot 已经创建了接口的实现类并放在容器里面等待使用了。**

学习：

1. @SpringBootApplication中集成了@ComponentScan注解 , 默认扫描Application同级包及子级包中的Bean , 但是会自动过滤掉接口
2. @MapperScan注解会扫描所有的dao层接口 , 无论带不带注解都会扫描并创建bean ,如果dao层接口上使用@Mapper注解 , 则可以不用包扫描注解 , 二者选以即可
3. resources下是类目录classpath:

## 十一、spring security

**完整学完**

学习地址：https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg==&mid=2247483957&idx=1&sn=fc30511490b160cd1519e7a7ee3d4ed0&scene=19#wechat_redirect

项目实战：idea工程的springboot

### 坑

1. **There is no PasswordEncoder mapped for the id “null”**

   https://blog.csdn.net/wondersfan/article/details/126768596

2. 各个版本差别很大

### 流程总结：

导入俩个包，一个security一个thymeleaf和security整合的（为了用 sec:authorize="isAuthenticated()"以及hasRole('vip1')），自己写SecurityConfig重写configure(HttpSecurity http)开启设置认证，以及configure(AuthenticationManagerBuilder auth)定义认证规则。

security可以用来定义哪些是公共页面，哪些需要访问权限，拥有一套它自己的登录页面，验证逻辑以及记住我注销等（你也可以自己做，但是其实只是套了一层皮肤，最后还是会调用它的逻辑）。security当然也可以为每个用户赋予权限和判断是否登录。

### 注意事项

## 十二、Shiro

### 1、概述

#### 简介

Shiro是Apache旗下的一个开源项目，它是一个非常易用的安全框架，提供了包括认证、授权、加密、会话管理等功能，与Spring Security一样**属基于权限的安全框架**，但是与Spring Security 相比，Shiro使用了比较简单易懂易于使用的授权方式。Shiro属于轻量级框架，相对于Spring Security简单很多，并没有security那么复杂。

Apache Shiro是一个强大且易用的Java安全框架

可以完成**身份验证、授权、密码和会话管理**

Shiro 不仅可以用在 JavaSE 环境中，也可以用在 JavaEE 环境中

官网： http://shiro.apache.org/

#### 功能

![在这里插入图片描述](assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ0OTgzOA==,size_16,color_FFFFFF,t_70.png)

**Authentication**：身份认证/登录，验证用户是不是拥有相应的身份；

**Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个权限；即判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的验证某个用户对某个资源是否具有某个权限；

**Session Manager**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是普通JavaSE环境的，也可以是如Web环境的；

**Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

**Web Support**：Web支持，可以非常容易的集成到Web环境；

**Caching**：缓存，比如用户登录后，其用户信息、拥有的角色/权限不必每次去查，这样可以提高效率；

**Concurrency**：shiro支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；

**Testing**：提供测试支持；

**Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

**Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了。

#### 从外部看

![在这里插入图片描述](assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ0OTgzOA==,size_16,color_FFFFFF,t_70-20230723231538426.png)

**应用代码直接交互的对象是Subject，也就是说Shiro的对外API核心就是Subject；其每个API的含义：**

**Subject**：主体，代表了当前“用户”，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject，如网络爬虫，机器人等；即一个抽象概念；所有Subject都绑定到SecurityManager，与Subject的所有交互都会委托给SecurityManager；可以把Subject认为是一个门面；SecurityManager才是实际的执行者；

**SecurityManager**：安全管理器；即所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；可以看出它是Shiro的核心，它负责与后边介绍的其他组件进行交互，如果学习过SpringMVC，你可以把它看成DispatcherServlet前端控制器；

**Realm**：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。

**也就是说对于我们而言，最简单的一个Shiro应用：**

  1、应用代码通过Subject来进行认证和授权，而Subject又委托给SecurityManager；

  2、我们需要给Shiro的SecurityManager注入Realm，从而让SecurityManager能得到合法的用户及其权限进行判断。

从以上也可以看出，Shiro不提供维护用户/权限，而是通过Realm让开发人员自己注入

#### 外部架构

![在这里插入图片描述](assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ0OTgzOA==,size_16,color_FFFFFF,t_70-20230723231727068.png)

**Subject**：主体，可以看到主体可以是任何可以与应用交互的“用户”；

**SecurityManager**：相当于SpringMVC中的DispatcherServlet或者Struts2中的FilterDispatcher；是Shiro的心脏；所有具体的交互都通过SecurityManager进行控制；它管理着所有Subject、且负责进行认证和授权、及会话、缓存的管理。

**Authenticator**：认证器，负责主体认证的，这是一个扩展点，如果用户觉得Shiro默认的不好，可以自定义实现；其需要认证策略（Authentication Strategy），即什么情况下算用户认证通过了；

**Authorizer**：授权器，或者访问控制器，用来决定主体是否有权限进行相应的操作；即控制着用户能访问应用中的哪些功能；

**Realm**：可以有1个或多个Realm，可以认为是安全实体数据源，即用于获取安全实体的；可以是JDBC实现，也可以是LDAP实现，或者内存实现等等；由用户提供；注意：Shiro不知道你的用户/权限存储在哪及以何种格式存储；所以我们一般在应用中都需要实现自己的Realm；

**SessionManager**：如果写过Servlet就应该知道Session的概念，Session呢需要有人去管理它的生命周期，这个组件就是SessionManager；而Shiro并不仅仅可以用在Web环境，也可以用在如普通的JavaSE环境、EJB等环境；所有呢，Shiro就抽象了一个自己的Session来管理主体与应用之间交互的数据；这样的话，比如我们在Web环境用，刚开始是一台Web服务器；接着又上了台EJB服务器；这时想把两台服务器的会话数据放到一个地方，这个时候就可以实现自己的分布式会话（如把数据放到Memcached服务器）；

**SessionDAO**：DAO大家都用过，数据访问对象，用于会话的CRUD，比如我们想把Session保存到数据库，那么可以实现自己的SessionDAO，通过如JDBC写到数据库；比如想把Session放到Memcached中，可以实现自己的Memcached SessionDAO；另外SessionDAO中可以使用Cache进行缓存，以提高性能；

**CacheManager**：缓存控制器，来管理如用户、角色、权限等的缓存的；因为这些数据基本上很少去改变，放到缓存中后可以提高访问的性能

####                                                                                                                          认证流程

![在这里插入图片描述](assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ0OTgzOA==,size_16,color_FFFFFF,t_70-20230723232017874.png)

**用户** 提交 **身份信息、凭证信息** 封装成 **令牌** 交由 **安全管理器** 认证

### 2、快速入门

#### 通过官网拷贝案例

https://shiro.apache.org/10-minute-tutorial.html

1. 下载shiro

2. 创建一个maven工程

3. 导入依赖

   ```xml
   <dependencies>
       <dependency>
           <groupId>org.apache.shiro</groupId>
           <artifactId>shiro-core</artifactId>
           <version>1.4.1</version>
       </dependency>    
   <!-- configure logging -->
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>jcl-over-slf4j</artifactId>
           <version>1.7.21</version>
       </dependency>
       <dependency>
           <groupId>org.slf4j</groupId>
           <artifactId>slf4j-log4j12</artifactId>
           <version>1.7.21</version>
       </dependency>
       <dependency>
           <groupId>log4j</groupId>
           <artifactId>log4j</artifactId>
           <version>1.2.17</version>
       </dependency>
   </dependencies>
   ```
   
4. 复制官网sample案例下的log4j和shiro.ini以及Quickstart.java

   <img src="assets/image-20230723235449512.png" alt="image-20230723235449512" style="zoom:50%;" />

5. 执行Quickstart.java

   ![image-20230723235529207](assets/image-20230723235529207.png)

#### 分析案例 

![img](assets/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDQ0OTgzOA==,size_16,color_FFFFFF,t_70-20230723235710728.png)

<img src="assets/image-20230723235644747.png" alt="image-20230723235644747" style="zoom:50%;" />

备注：我们学习如何使用的时候可以下载她的源码，然后找到simple目录下，有很多对应的案例

### 3、 springboot集成

接着跟着https://blog.csdn.net/weixin_45433031/article/details/119686182完成直到完成登录拦截，再去看课

做完了登录拦截了，一直报javax/servlet/Filter错误，没法进行了，然后引入servlet jar，不报错了，但是设置了登录拦截，但是无效。（打算放弃这里了

在研究一下spring shiro和mybatis的集成

## 十三、swagger

笔记：https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg%3D%3D&chksm=ce6104a6f9168db05d089112c42893cf9f5af434a2cbe2c5b7dc469576ad4d8dcb5ddcddc0db&idx=1&mid=2247483909&scene=21&sn=201ee629b9ce3b9276a263e18812e607#wechat_redirect

备用链接：https://drive.google.com/file/d/1siLSTusyx3Pv0288EKpu2oD-J3JAO9Ur/view?usp=drive_link

配置扫描接口时候用 select 去指定我们需要的接口

1. 空接口

   <img src="assets/image-20230926114802968.png" alt="image-20230926114802968" style="zoom:50%;" />

2. 扫描到接口了(指定了只扫描 controller，所以看不到/error 接口)

   <img src="assets/image-20230926115044906.png" alt="image-20230926115044906" style="zoom:50%;" />

配置 enable

1. 禁用后

   <img src="assets/image-20230926115522000.png" alt="image-20230926115522000" style="zoom:50%;" />

**bug**：springboot3.1.4和 springboot2.7.x 整合 swagger2 时，会出现bug, 版本调整至 2.5.0 问题解决

```java
Failed to start bean 
'documentationPluginsBootstrapper'; nested exception is 
```

**注意：**不设置 selector 的情况下，默认是扫描所有接口

## 十四、异步任务，定时任务以及邮件任务

### 异步任务

其实只是添加俩个注解

1. @EnableAsync(用在启动类上)

   ```java
   @EnableAsync
   @SpringBootApplication
   public class Sb11AsyncSyncEmailApplication {
       public static void main(String[] args) {
           SpringApplication.run(Sb11AsyncSyncEmailApplication.class, args);
       }
   }
   ```

2. @Async(用在方法上)

   ```java
   @Service
   public class AsyncService {
       @Async//异步任务
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
   ```

个人解释：

我们贴上 controller 的代码，如下

```java
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/invoke")
    public String invoke() throws ExecutionException, InterruptedException {
        Future data = asyncService.process();
        return data.get() + " result is here";
    }
}
```

如果采用正常执行，也就是同步任务：访问“/invoke”, 我们必须等待 asyncservice 完成处理数据的任务后，页面才进入。

如果我们添加上注解：页面瞬间进入，而不必等等数据处理。**注意：**在使用注解@Async的方法上，我们通常设置返回值为 void，因为主线成没有办法立刻获取到返回值，这会导致程序崩溃。或者我们也可以设置返回值为 java.util.concurrent.Future，这个类可以获取到未来的值，但是如果你尝试获取它通过 future.get()（这其实是一个堵塞性等待），程序又会坍塌成同步任务。

### Email

gmail：https://pabasararathnayake.medium.com/spring-boot-application-to-send-emails-using-smtp-protocol-c2616d7edf92#:~:text=A%20SimpleMailMessage%20object%20is%20created,is%20injected%20by%20Spring%20Boot.

带附件：https://stackoverflow.com/questions/70684541/java-io-ioexception-exception-writing-multipart-with-springboot

狂神（qq mail）：https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg%3D%3D&chksm=ce6104adf9168dbb31c6306c840c575d10a3c3c9e886a7e5f3d1a67882a6eacdb40ac0267688&idx=1&mid=2247483918&scene=21&sn=afadfe906c5f15887fa8e7dad3c8980c#wechat_redirect

具体代码实现见项目

### Scheduler

就是设置一下定时任务，就俩注解@EnableScheduling 和@Scheduled(corn="0 * * * * 0-7")以及 corn 表达式，就没了

参考这个就行了：https://drive.google.com/file/d/1vskz-_WfN7nBIabIxXy9B2_S93R0B4Ep/view?usp=drive_link

## 十五、整合 Redis 

学完 Redis 之后再来看这个

## 十六、 Dubbo， zookeeper

**用来做分布式系统的**

已经学完了，只是没有写代码实际操作罢了。

学习笔记：https://mp.weixin.qq.com/s?__biz=Mzg2NTAzMTExNg%3D%3D&chksm=ce610488f9168d9eee180472c9e225c737ed56075370c1174eb29ae214326a5f8e49147c2d65&idx=1&mid=2247483947&scene=21&sn=0c8efabbaf9b8ca835d862e6e0a2254f#wechat_redirect

学习笔记备用：https://drive.google.com/file/d/1d0g3bITQWuK6cTWU08ahozyjTDbEH7sa/view?usp=drive_link

官网学习笔记：https://dubbo.apache.org/en/docs3-v2/java-sdk/quick-start/

个人理解总结：dubbo 其实就几行注释，重要是要理解他们在干什么，dubbo 这样的 rpc 通讯框架需要有一个 registry，dubbo 推荐默认用（可以理解成服务器，用来部署方法，就像 tomcat 一样，把你想要远程调用的方法放到服务器上）。

###### RPC通讯协议 和 RESTful API疑惑

- **RESTful API 是一种设计方式（或者说是一种架构风格），** 它基于 **REST 架构风格**，使用**标准的HTTP协议**，强调资源的概念，**通过HTTP方法（GET、POST、PUT、DELETE等）**对**资源**进行操作。RESTful API 是一种**设计理念**，关注于如何使用HTTP协议构建API，提供了一种简单、灵活、可扩展的API设计方式。（其实是通过函数access 资源
- **RPC 是一种协议框架（Remote Procedure Call，远程过程调用），** 它是一种实现分布式系统中程序之间通信的技术。RPC 允许程序调用远程服务器上的函数（关注点在**函数**），而不需要程序员显式编写网络通信的代码。RPC 通常基于特定的通信协议，如Protocol Buffers、Thrift等，可以支持多种编程语言和平台之间的通信。通讯采用的是 TCP/IP 协议

## 十七，聊聊

<img src="assets/image-20230929183821654.png" alt="image-20230929183821654" style="zoom:50%;" />

<img src="assets/image-20230929183641019.png" alt="image-20230929183641019" style="zoom:50%;" />

<img src="assets/image-20230929183715990.png" alt="image-20230929183715990" style="zoom:50%;" />

## bug

### 1、sb项目404

https://blog.csdn.net/weixin_41276238/article/details/103511130

### 2、yml无提示

### 3、springboot整合shiro一直显示

springboot项目出现Exception in thread “main“ java.lang.NoClassDefFoundError: javax/servlet/Filter

https://blog.csdn.net/qq_41618008/article/details/128551110

### 4、springboot启动时报错 no main manifest attribute, in XXX1.0.0-SNAPSHOT.jar

maven 项目缺少插件

https://blog.csdn.net/qing_mei_xiu/article/details/83832941

## 额外知识：

1. springboot 打包成 war 包操作

这个视频的 21 分钟有详细讲解。

https://www.bilibili.com/video/BV1uK411p7Bp/?spm_id_from=333.337.search-card.all.click&vd_source=c5e9f4d8a896a6ff07847db4ecfe8fc8

2. 在 maven pom 下的 properties 设置数据再下面引用

   用`${}`标记

3. maven 打包指定函数入口类

   https://juejin.cn/s/maven%E6%89%93%E5%8C%85%E6%8C%87%E5%AE%9Amain%E5%87%BD%E6%95%B0




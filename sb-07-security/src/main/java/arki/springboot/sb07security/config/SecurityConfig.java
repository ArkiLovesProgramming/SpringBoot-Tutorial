package arki.springboot.sb07security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 定制请求的授权规则
        // 首页所有人可以访问
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level/*1").hasRole("vip1")
                .antMatchers("/level/*2").hasRole("vip2")
                .antMatchers("/level/*3").hasRole("vip3");

        // 开启自动配置的登录功能
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
//        http.formLogin(); //没有自定义表单的时候可以这样写

        http.formLogin()    //自定义表单了，需要接收我们表单的参数
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/tologin")
                .loginProcessingUrl("/login"); // 登陆表单提交请求

        //....
        //开启自动配置的注销的功能
        // /logout 注销请求
        http.logout();
        // .logoutSuccessUrl("/"); 注销成功来到首页
        http.logout().logoutSuccessUrl("/");

        //。。。。。。。。。。。
        //记住我
//        http.rememberMe();
        //定制记住我的参数！
        http.rememberMe().rememberMeParameter("remember");


        //自定义登录页面，只是页面是自己的，其中的验证还是他做，用/toLogin去进入登录页面，/login失效
        http.formLogin().loginPage("/tologin");
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义，也可以在jdbc中去拿....
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        //spring security 官方推荐的是使用bcrypt加密方式。
        //在内存中定义，也可以在jdbc中去拿....
        auth.inMemoryAuthentication()
                .withUser("arki").password("{bcrypt}" + new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("admin").password("{bcrypt}" + new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password("{bcrypt}" + new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2");
    }
}
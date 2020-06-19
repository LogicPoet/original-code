package com.cat.security.config;

import com.cat.security.filter.LoginFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

/**
 * 配置类的优先级高于application配置文件[此时会覆盖掉配置文件中的相同配置]
 *
 * @author LZ
 * @date 2020/6/19 9:51
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置身份验证管理器生成器参数
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()//开启内存方式配置用户信息
                .withUser("tom")
                .password("123")
                .roles("admin")
                .and()
                //在没有 Spring Boot 的时候，我们都是 SSM 中使用 Spring Security，这种时候都是在 XML 文件中配置 Spring Security，
                //既然是 XML 文件，标签就有开始有结束，现在的 and 符号相当于就是 XML 标签的结束符，表示结束当前标签，
                //这个时候上下文会回到 inMemoryAuthentication 方法中，然后开启新用户的配置。
                .withUser("jack")
                .password("123")
                .roles("dev")
        ;
    }

    /**
     * 网络安全配置
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()//忽略，放行被匹配到的路径资源, 用来配置忽略掉的 URL 地址，一般对于静态文件
                .antMatchers("/js/**", "/css/**","/images/**")//以不区分大小写的方式匹配。
        ;
    }

    /**
     * Http安全性配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置授权请求
                .anyRequest().authenticated()//任何请求都需要认证
                .and() //and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置。
                .formLogin()//配置登陆请求
                .loginPage("/login.html")//当我们定义了登录页面为 /login.html 的时候，Spring Security 也会帮我们自动注册一个 /login.html 的接口，这个接口是 POST 请求，用来处理登录逻辑
                .permitAll()//表示登录相关的页面/接口不要被拦截
                .and()
                .csrf().disable();//关闭 csrf
        //用自定义的 LoginFilter 实例代替 UsernamePasswordAuthenticationFilter
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 替换登陆器
     *
     * @return
     * @throws Exception
     */
    @Bean
    LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        //设置成功验证用户时调用的处理器
        loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                UserDetails principal = (UserDetails)authentication.getPrincipal();
                String ok = principal.getUsername()+"登录成功!";
                String s = new ObjectMapper().writeValueAsString(ok);
                out.write(s);
                out.flush();
                out.close();
            }
        });
        //设置身份验证失败时调用的处理器
        loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                String respBean ="";
                if (exception instanceof LockedException) {
                    respBean="账户被锁定，请联系管理员!";
                } else if (exception instanceof CredentialsExpiredException) {
                    respBean="密码过期，请联系管理员!";
                } else if (exception instanceof AccountExpiredException) {
                    respBean="账户过期，请联系管理员!";
                } else if (exception instanceof DisabledException) {
                    respBean="账户被禁用，请联系管理员!";
                } else if (exception instanceof BadCredentialsException) {
                    respBean="用户名或者密码输入错误，请重新输入!";
                }
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/doLogin");
        return loginFilter;
    }

}

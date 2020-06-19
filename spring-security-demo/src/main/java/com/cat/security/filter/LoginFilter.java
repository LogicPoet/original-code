package com.cat.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义登陆过滤器，重写spring-security默认的过滤器
 * ps:默认的过滤器，参数是key/value方式获取的
 * 这里需要格外支持以json的方式获取
 *
 * @author LZ
 * @date 2020/6/19 10:34
 **/
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 尝试身份验证
     * ps：
     * 1、首先登录请求肯定是 POST，如果不是 POST ，直接抛出异常，后面的也不处理了。
     * 2、因为要在这里处理验证码，所以第二步从 session 中把已经下发过的验证码的值拿出来。
     * 3、接下来通过 contentType 来判断当前请求是否通过 JSON 来传递参数，如果是通过 JSON 传递参数，则按照 JSON 的方式解析，如果不是，则调用 super.attemptAuthentication 方法，进入父类的处理逻辑中，也就是说，我们自定义的这个类，既支持 JSON 形式传递参数，也支持 key/value 形式传递参数。
     * 4、如果是 JSON 形式的数据，我们就通过读取 request 中的 I/O 流，将 JSON 映射到一个 Map 上。
     * 5、从 Map 中取出 code，先去判断验证码是否正确，如果验证码有错，则直接抛出异常。验证码的判断逻辑，大家可以参考：松哥手把手教你给微人事添加登录验证码。
     * 6、接下来从 Map 中取出 username 和 password，构造 UsernamePasswordAuthenticationToken 对象并作校验。
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            password = password.trim();
            log.info("登陆用户名：{}，密码：{}",username,password);
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}

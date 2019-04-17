package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.UserService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.api.base.BaseController;
import com.libi.accountbook.web.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 */
@RestController
@PropertySource("classpath:app-param.properties")
public class LoginController extends BaseController {
    @Value("${cookie-timeout}")
    private Long cookieTimeout;
    @Reference
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisUtil redisUtil;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**登陆成功*/
    @RequestMapping(LOGIN_SUCCESS_URL)
    public ResponseDto loginSuccess(HttpServletRequest request, HttpServletResponse response) {
        //生成token
        String token = UUID.randomUUID().toString();
        //token存在Redis里,键值对是Id，token
        redisUtil.setString(getLoginUser().getId().toString(), token, cookieTimeout);
        //token通过Cookie返回
        Cookie cookie = new Cookie("TokenLogin", token);
        cookie.setPath("/");
        cookie.setMaxAge(Math.toIntExact(cookieTimeout));
        logger.info("id为："+getLoginUser().getId()+"的用户登陆成功，Token是："+token);
        response.addCookie(cookie);
        return new ResponseDto(0,"登陆成功",getLoginUser());
    }

    /**登陆失败*/
    @RequestMapping(LOGIN_FAIL_URL)
    public ResponseDto loginFail() {
        return new ResponseDto(1, "登陆失败", null);
    }

    /**登出成功*/
    @RequestMapping(LOGOUT_SUCCESS_URL)
    public ResponseDto logoutSuccess() {
        return new ResponseDto(0, "登出成功", null);
    }

    /**注册用户*/
    @RequestMapping(value = REGISTER_URL,method = RequestMethod.POST)
    public ResponseDto register(AccUser user,HttpServletRequest request) throws ParamNotFindException {
        if (user.getUserName() == null) {
            notFindParams.add("userName");
        }
        if (user.getPassword() == null) {
            notFindParams.add("password");
        }
        throwParamNotFindException(request.getRequestURI());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("转码后的密码:"+user.getPassword()+",length:"+user.getPassword().length());
        userService.insertUser(user);
        return new ResponseDto(0, "注册成功", new UserDto(userService.selectUserByUserName(user.getUserName())));
    }
}

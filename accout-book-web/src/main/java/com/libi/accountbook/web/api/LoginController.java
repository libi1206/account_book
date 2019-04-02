package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.libi.accountbook.web.constant.UrlConst.*;
/**
 * @author libi
 */
@RestController
public class LoginController extends BaseController{
    @Reference
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(LOGIN_SUCCESS_URL)
    public ResponseDto loginSuccess() {
        return new ResponseDto(0,"登陆成功",null);
    }

    @RequestMapping(LOGIN_FAIL_URL)
    public ResponseDto loginFail() {
        return new ResponseDto(1, "登陆失败", null);
    }

    @RequestMapping(LOGOUT_SUCCESS_URL)
    public ResponseDto logoutSuccess() {
        return new ResponseDto(0, "登出成功", null);
    }

    @RequestMapping(value = REGISTER_URL,method = RequestMethod.POST)
    public ResponseDto register(AccUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("转码后的密码:"+user.getPassword()+",length:"+user.getPassword().length());
        userService.insertUser(user);
        return new ResponseDto(0, "注册成功", new UserDto(userService.selectUserByUserName(user.getUserName())));
    }
}

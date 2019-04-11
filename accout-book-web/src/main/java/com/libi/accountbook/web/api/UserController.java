package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.service.UserService;
import com.libi.accountbook.web.anno.CheckToken;
import com.libi.accountbook.web.anno.RedisTransaction;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 */
@RestController
@RequestMapping(USER_ROOT)
public class UserController extends BaseController {
    @Reference
    private UserService userService;

    @CheckToken
    @RequestMapping("/me")
    public ResponseDto getMe(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseDto(0, "成功", getLoginUser());
    }

    @CheckToken
    @RequestMapping("/update")
    public ResponseDto update(UserDto userDto,HttpServletRequest request,HttpServletResponse response) {
        userDto.setId(getLoginUser().getId());
        userDto.setCreateTime(null);
        return new ResponseDto(0, "更新成功", userService.updateById(userDto));
    }
}

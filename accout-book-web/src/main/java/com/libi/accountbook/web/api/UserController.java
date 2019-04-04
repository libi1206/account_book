package com.libi.accountbook.web.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.service.UserService;
import com.libi.accountbook.web.api.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 */
@RestController
@RequestMapping(USER_ROOT)
public class UserController extends BaseController {
    @Reference
    private UserService userService;

    @RequestMapping("/me")
    public ResponseDto getMe() {
        return new ResponseDto(0, "成功", getLoginUser());
    }

    @RequestMapping("/update")
    public ResponseDto update(UserDto userDto) {
        return new ResponseDto(0, "更新成功", userService.updateById(userDto));
    }
}

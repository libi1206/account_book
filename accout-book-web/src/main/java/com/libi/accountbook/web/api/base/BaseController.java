package com.libi.accountbook.web.api.base;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * @author libi
 */
public abstract class BaseController {
    @Reference
    private UserService userService;

    protected UserDto getLoginUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUser user = userService.selectUserByUserName(userDetails.getUsername());
        return new UserDto(user);
    }

}

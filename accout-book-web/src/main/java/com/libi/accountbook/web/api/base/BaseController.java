package com.libi.accountbook.web.api.base;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.dto.ResponseDto;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.exception.ParamNotFindException;
import com.libi.accountbook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;


/**
 * @author libi
 */
public abstract class BaseController {
    @Reference
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    protected List<String> notFindParams = new ArrayList<>();

    protected void throwParamNotFindException (String uri) throws ParamNotFindException {
        if (notFindParams.size()>0) {
            ParamNotFindException paramNotFindException = new ParamNotFindException();
            paramNotFindException.setParamsName(new ArrayList<String>(notFindParams));
            paramNotFindException.setUri(uri);
            paramNotFindException.setCurrentTime(System.currentTimeMillis());
            notFindParams.clear();
            throw paramNotFindException;
        }
    }

    protected UserDto getLoginUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccUser user = userService.selectUserByUserName(userDetails.getUsername());
        return new UserDto(user);
    }

}

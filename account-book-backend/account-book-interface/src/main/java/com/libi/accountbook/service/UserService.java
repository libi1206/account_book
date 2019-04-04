package com.libi.accountbook.service;

import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;

/**
 * @author libi
 */
public interface UserService {

    AccUser selectUserById(Long id);

    AccUser selectUserByUserName(String userName);

    AccUser selectUserByPhone(String phone);

    boolean insertUser(AccUser accUser);

    UserDto updateById(UserDto userDto);
}

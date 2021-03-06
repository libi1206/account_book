package com.libi.accountbook.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.libi.accountbook.dao.AccUserDAO;
import com.libi.accountbook.dto.UserDto;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author libi
 */
@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccUserDAO accUserDAO;

    @Override
    public AccUser selectUserById(Long id) {
        return accUserDAO.selectByPrimaryKey(id);
    }

    @Override
    public AccUser selectUserByUserName(String userName) {
        return accUserDAO.selectUserByUserName(userName);
    }

    @Override
    public AccUser selectUserByPhone(String phone) {
        return accUserDAO.selectUserByPhone(phone);
    }

    @Override
    public boolean insertUser(AccUser accUser) {
        accUser.setCreateTime(System.currentTimeMillis());
        return accUserDAO.insertSelective(accUser)==1;
    }

    @Override
    public UserDto updateById(UserDto userDto) {
        AccUser user = new AccUser(userDto);
        accUserDAO.updateByPrimaryKeySelective(user);
        return new UserDto(accUserDAO.selectByPrimaryKey(user.getId()));
    }

    @Override
    public void deleteById(Long userId) {
        accUserDAO.deleteByPrimaryKey(userId);
    }

}

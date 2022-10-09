package com.forum.server.Impl;

import com.forum.Exception.ExceptionEnum;
import com.forum.Exception.ForumException;
import com.forum.model.dao.UserMapper;

import com.forum.model.pojo.User;
import com.forum.server.UserService;
import com.forum.untils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void register(String username, String password) throws ForumException {
        /*check if the user has the same name*/
        User result = userMapper.selectByName(username);
        if (result != null) {
            throw new ForumException(ExceptionEnum.DUPLICATE_NAME);
        }
        /*write to database*/
        User user = new User();
        user.setUsername(username);
        try {
            user.setPassword(MD5Utils.getMDstr(password));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new ForumException(ExceptionEnum.INSERT_FAILED);
        }
    }

    @Override
    public User login(String userName, String password) throws ForumException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMDstr(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        User user = userMapper.selectByName(userName);
        if ( user== null){
            throw new ForumException(ExceptionEnum.USER_ERROR);
        }else if(!user.getPassword().equals(md5Password)){
            System.out.println(user.getPassword());
            throw new ForumException(ExceptionEnum.PASSWORD_WRONG);
        }

        return user;
    }
}

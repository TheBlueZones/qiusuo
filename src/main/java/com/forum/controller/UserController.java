package com.forum.controller;

import com.forum.Exception.ExceptionEnum;
import com.forum.Exception.ForumException;
import com.forum.common.ApiResponse;
import com.forum.common.Constant;
import com.forum.model.pojo.User;
import com.forum.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse register(@RequestParam("username") String username,
                                @RequestParam("password") String password) throws ForumException {
        /*null和空字符串有区别*/
        if (!StringUtils.hasText(username)) {
            return ApiResponse.error(ExceptionEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(password)) {
            return ApiResponse.error(ExceptionEnum.PASSWORD_NOT_NULL);
        }
        if (password.length() < 8) {
            return ApiResponse.error(ExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(username, password);
        return ApiResponse.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiResponse login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session) throws ForumException {
        if (!StringUtils.hasText(username)) {
            return ApiResponse.error(ExceptionEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(password)) {
            return ApiResponse.error(ExceptionEnum.PASSWORD_NOT_NULL);
        }

        User user = userService.login(username, password);
        /*don't storage password*/
        user.setPassword(null);
        /*服务端把用户信息保存在session里面*/
        session.setAttribute(Constant.FORUM, user);
        return ApiResponse.success(user);
    }


}

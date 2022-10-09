package com.forum.server;

import com.forum.model.pojo.User;

public interface UserService {

    void register(String username, String password) ;

    User login(String userName, String password) ;
}

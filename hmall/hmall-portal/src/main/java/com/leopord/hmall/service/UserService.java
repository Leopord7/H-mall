package com.leopord.hmall.service;

import com.leopord.hmall.entity.User;

public interface UserService {

    User getUserByName(String name);

    int addUser(User user);
}

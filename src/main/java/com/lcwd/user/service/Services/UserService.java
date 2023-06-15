package com.lcwd.user.service.Services;

import com.lcwd.user.service.Entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUser();
    User getUser(int userId);

}

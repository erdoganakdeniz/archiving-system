package com.archivingsystem.service;

import com.archivingsystem.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User save(User user);

}

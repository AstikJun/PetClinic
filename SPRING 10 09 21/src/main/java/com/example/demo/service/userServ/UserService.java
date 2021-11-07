package com.example.demo.service.userServ;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    void deleteUserById(Integer id);
    void deleteAllUsers();
    User updateUser(User user);
    User getUserById(Integer id);
    List<User> getAllUsers();
    User register(User user);
    User getUserByEmail(String email);
}

package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.User;

import java.util.Map;

public interface UserService {
    Map<Long, User> getAllUsers();
    User getUserById(Long userId);
    User getUserByName(String name);
    void updateUser(User userToUpdate);
    void addUser(User newUser);
    void deleteUser(Long userId);
    boolean isUniqueName(String name);
}

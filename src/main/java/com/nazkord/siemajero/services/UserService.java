package com.nazkord.siemajero.services;

import com.nazkord.siemajero.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getUserByName(String name);
    void updateUser(User userToUpdate);
    void addUser(User newUser);
    void deleteUser(Long userId);
    boolean isUniqueName(String name);
}

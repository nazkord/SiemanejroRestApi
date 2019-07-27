package com.nazkord.siemajero.Services;

import com.nazkord.siemajero.Model.User;

import java.util.Map;

public interface UserService {
    Map<Long, User> getAllUsers();
    User getUserById(Long userId);
    void updateUser(User userToUpdate, Long userId);
    void addUser(User newUser);
    void deleteUser(Long userId);

}

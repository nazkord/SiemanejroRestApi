package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.repositories.UserRepository;
import com.nazkord.siemajero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DbBasedUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<Long, User> getAllUsers() {
        Map<Long, User> users = new HashMap<>();
        userRepository.findAll().forEach(user -> users.put(user.getId(), user));
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        Optional optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return (User) optionalUser.get();
        }
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void updateUser(User userToUpdate) {
        userRepository.save(userToUpdate);
    }

    @Override
    public void addUser(User newUser) {
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(getUserById(userId));
    }
}

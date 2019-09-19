package com.nazkord.siemajero.dbBasedService;

import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.repositories.UserRepository;
import com.nazkord.siemajero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DbBasedUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            User user = (User) optionalUser.get();
            user.setPassword("#######");
            return user;
        }
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name).get();
    }

    @Override
    public void updateUser(User user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userToUpdate.copyFrom(user);
        userRepository.save(user);
    }

    @Override
    public void addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.delete(getUserById(userId));
    }

    @Override
    public boolean isUniqueName(String name) {
        Optional optionalUser = userRepository.findByName(name);
        return !optionalUser.isPresent();
    }
}

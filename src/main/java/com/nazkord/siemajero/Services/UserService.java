package com.nazkord.siemajero.Services;

import com.nazkord.siemajero.Model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private Map<Long, User> users = new HashMap<Long, User>() {{
        put(Long.valueOf(1), new User(Long.valueOf(1),"Nazar", new ArrayList<>()));
        put(Long.valueOf(2), new User(Long.valueOf(2),"Ola", new ArrayList<>()));
        put(Long.valueOf(3), new User(Long.valueOf(3),"Taras", new ArrayList<>()));
        put(Long.valueOf(4), new User(Long.valueOf(4),"Kuba", new ArrayList<>()));
    }};

    public Map<Long,User> getAllUsers() {
        return users;
    }

    public User getUserById(Long userId) {
        return users.get(userId);
    }

    public void updateUser(User userToUpdate, Long userId) {
        if(users.containsKey(userId))
            users.replace(userId,userToUpdate);
    }

    public void addUser(User newUser) {
        users.put(newUser.getId(), newUser);
    }

    public void deleteUser(Long userId) {
        users.remove(userId);
    }


}

package com.nazkord.siemajero.MapBasedServices;

import com.nazkord.siemajero.Model.User;
import com.nazkord.siemajero.Services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapBasedUserService implements UserService {
    private Map<Long, User> users = new HashMap<Long, User>() {{
        put(1L, new User(1L,"Nazar", new ArrayList<>()));
        put(2L, new User(2L,"Ola", new ArrayList<>()));
        put(3L, new User(3L,"Taras", new ArrayList<>()));
        put(4L, new User(4L,"Kuba", new ArrayList<>()));
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

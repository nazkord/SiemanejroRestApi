package com.nazkord.siemajero.mapBasedServices;

import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.services.UserService;

import java.util.HashMap;
import java.util.Map;

public class MapBasedUserService implements UserService {
    private Map<Long, User> users = new HashMap<Long, User>() {{
        put(1L, new User(1L,"Nazar"));
        put(2L, new User(2L,"Ola"));
        put(3L, new User(3L,"Taras"));
        put(4L, new User(4L,"Kuba"));
    }};

    public Map<Long,User> getAllUsers() {
        return users;
    }

    public User getUserById(Long userId) {
        return users.get(userId);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    public void updateUser(User userToUpdate) {
       // if(users.containsKey(userId))
            users.replace(userToUpdate.getId(), userToUpdate);
    }

    public void addUser(User newUser) {
        users.put(newUser.getId(), newUser);
    }

    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    @Override
    public boolean isUniqueName(String name) {
        return false;
    }
}

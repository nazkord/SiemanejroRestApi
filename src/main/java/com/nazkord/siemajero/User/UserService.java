package com.nazkord.siemajero.User;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users = new ArrayList<>(Arrays.asList(
            new User(1,"Nazar", 19, "blabla"),
            new User(2,"Ola", 18, "olabzowy"),
            new User(3, "Taras", 7, "tkord")
    ));

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int user_id) {
        // TODO: change to lambdas
        for (User user:
                users) {
            if(user.getUser_id() == user_id)
                return user;
        }
        return null;
    }

    public void updateUser(User userToUpdate, int userId) {
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getUser_id() == userId) {
                users.set(i, userToUpdate);
                return;
            }
        }
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public void deleteUser(int userId) {
        users.remove(getUserById(userId));
    }


}

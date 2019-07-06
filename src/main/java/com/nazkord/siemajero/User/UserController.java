package com.nazkord.siemajero.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void postUser(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userIdToReplaced}")
    public void updateUser(@RequestBody User userToUpdate, @PathVariable int userIdToReplaced) {
        userService.updateUser(userToUpdate, userIdToReplaced);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

}


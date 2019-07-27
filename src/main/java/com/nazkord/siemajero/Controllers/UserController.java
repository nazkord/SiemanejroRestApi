package com.nazkord.siemajero.Controllers;

import com.nazkord.siemajero.Model.User;
import com.nazkord.siemajero.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping()
    public Map<Long, User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void postUser(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody User userToUpdate, HttpSession httpSession) {
        userService.updateUser(userToUpdate, getLoggedInUser(httpSession).getId());
    }

    // only for admin
    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    // TODO: make work for real user
    private User getLoggedInUser(HttpSession httpSession) {
        return userService.getUserById(1L);
    }

}


package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.security.Role;
import com.nazkord.siemajero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<Long, User> getAllUsers(SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(String.valueOf(Role.ADMIN))) {
            return userService.getAllUsers();
        } else {
            return Collections.emptyMap();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User getUser(@PathVariable Long userId, SecurityContextHolderAwareRequestWrapper securityWrapper) {

        if (isOperationPermitted(userId, securityWrapper)) {
            return userService.getUserById(userId);
        } else {
            return null;
        }
    }

    //TODO: make sure to check add only unique name
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@RequestBody User newUser, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(Role.ADMIN.name())) {
            try {
                userService.addUser(newUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                String errorMessage = e + " <== error";
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error while posting", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody User userToUpdate, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (isOperationPermitted(userId, securityWrapper)) {
            userToUpdate.setId(userId);
            userService.updateUser(userToUpdate);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Long userId, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(String.valueOf(Role.ADMIN))) {
            userService.deleteUser(userId);
        }
    }

    private boolean isOperationPermitted(Long userId, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(Role.ADMIN.name())) {
            return true;
        }
        // check whether the logged user change his own profile
        User currentUser = userService.getUserByName(securityWrapper.getRemoteUser());
        return currentUser.getId().equals(userId);
    }

}


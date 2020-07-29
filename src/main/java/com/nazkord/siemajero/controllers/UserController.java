package com.nazkord.siemajero.controllers;

import com.nazkord.siemajero.model.User;
import com.nazkord.siemajero.security.Role;
import com.nazkord.siemajero.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "the user API")
public class UserController {

    private final UserService userService;
    public static final String TOKEN_LOGIN = "/tokenSignIn";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users / get user by username", description = "All users available only for admin. Possibility to filter by username for everyone", tags = { "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))) })

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers(
            @Parameter(schema = @Schema(hidden = true))
                    SecurityContextHolderAwareRequestWrapper securityWrapper,
            @Parameter(description = "Filter users by username. By default is null")
            @RequestParam(required = false)
                    String userName) {

        if(userName == null) { // if userName doesnt exist
            if (securityWrapper.isUserInRole(Role.ADMIN.name())) { // get all bets if admin
                return userService.getAllUsers();
            } else {
                return null;
            }
        } else { // if exist
            if(isOperationPermitted(userService.getUserByName(userName).getId(), securityWrapper)) {
                User user = userService.getUserByName(userName);
                return Collections.singletonList(user);
            } else {
                return null;
            }
        }
    }

    @Operation(summary = "Find user by ID", description = "Return a single user", tags = { "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))) })

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public User getUser(
            @PathVariable
                    Long userId,
            @Parameter(schema = @Schema(hidden = true))
                    SecurityContextHolderAwareRequestWrapper securityWrapper) {

        if (isOperationPermitted(userId, securityWrapper)) {
            return userService.getUserById(userId);
        } else {
            return null;
        }
    }


    @Operation(summary = "Add new user", description = "", tags = { "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Provided user has no unique name")})

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postUser(
            @RequestBody
                    User newUser,
            @Parameter(schema = @Schema(hidden = true))
                    SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(Role.ADMIN.name())) {
            try {
                if(userService.isUniqueName(newUser.getName())) {
                    userService.addUser(newUser);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    String errorMessage = "Not UNIQUE name <== error";
                    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
                }
            } catch (Exception e) {
                String errorMessage = e + " <== error";
                return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Forbidden", HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @Operation(summary = "Update an existing user", description = "", tags = { "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "There is no user with provided id") })

    @RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable
                    Long userId,
            @RequestBody
                    User userToUpdate,
            @Parameter(schema = @Schema(hidden = true))
                    SecurityContextHolderAwareRequestWrapper securityWrapper) {
        try {
            if (isOperationPermitted(userId, securityWrapper)) {
                userToUpdate.setId(userId);
                userService.updateUser(userToUpdate);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>("bad permission <== error", HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            String errorMessage = "Error while updating <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete a user", description = "Available only for admin", tags = { "Users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")})

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(
            @PathVariable
                    Long userId,
            @Parameter(schema = @Schema(hidden = true))
                    SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(String.valueOf(Role.ADMIN))) {
            userService.deleteUser(userId);
        }
    }

    private boolean isOperationPermitted(Long userIdToCheck, SecurityContextHolderAwareRequestWrapper securityWrapper) {
        if (securityWrapper.isUserInRole(Role.ADMIN.name())) {
            return true;
        }
        // check whether the logged user want to get his own profile (getRemoteUser return name)
        User currentUser = userService.getUserByName(securityWrapper.getRemoteUser());
        return currentUser.getId().equals(userIdToCheck);
    }

}
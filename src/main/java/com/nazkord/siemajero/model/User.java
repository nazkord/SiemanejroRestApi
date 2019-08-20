package com.nazkord.siemajero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nazkord.siemajero.security.Role;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    private String roleName;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
//        this.bets = bets;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

//    @JsonIgnore
//    public Role getRole() {
//        return Role.valueOf(roleName);
//    }
//
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
//
//    public void setRole(Role role) {
//        this.roleName = role.name();
//    }
}


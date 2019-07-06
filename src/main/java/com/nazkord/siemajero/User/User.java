package com.nazkord.siemajero.User;

public class User {

    private int user_id;
    private String name;
    private int age;
    private String email;

    public User() {
    }

    public User(int user_id, String name, int age, String email) {
        this.user_id = user_id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


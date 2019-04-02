package com.cinema.model.entity;

import com.cinema.model.entity.enums.Role;

public class User {

    private int id;
    private String login;
    private String password;
    private String first_name;
    private String second_name;
    private String middle_name;
    private String email;
    private int phone;
    private Role role;


    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String last_name) {
        this.second_name = second_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

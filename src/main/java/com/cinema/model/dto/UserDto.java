package com.cinema.model.dto;

public class UserDto {

    private int id;
    private String login;
    private String password;

    public String getNickname() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}

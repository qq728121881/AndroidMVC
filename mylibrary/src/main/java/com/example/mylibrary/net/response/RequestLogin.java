package com.example.mylibrary.net.response;

import java.io.Serializable;

public class RequestLogin implements Serializable {

    /*
    * {
  "id": "",
  "newPassword": "",
  "password": "13111591939",
  "username": "123456"
}
    * */

    private String id;
    private String newPassword;
    private String password;
    private String username;

    public RequestLogin() {
    }

    public RequestLogin(String id, String newPassword, String password, String username) {
        this.id = id;
        this.newPassword = newPassword;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

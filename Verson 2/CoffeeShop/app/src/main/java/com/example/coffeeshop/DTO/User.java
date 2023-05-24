package com.example.coffeeshop.DTO;

public class User {
    private int id;
    private String username;
    private String _password;
    private  String full_name;
    private  String phonenumber;

    private  String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private  String email;

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public String get_username() {
        return username;
    }

    public void set_username(String username) {
        this.username = username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phonenumber;
    }

    public void setPhone_number(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public User() {
    }

    public User(int id, String username, String _password, String full_name, String phonenumber) {
        this.id = id;
        this.username = username;
        this._password = _password;
        this.full_name = full_name;
        this.phonenumber = phonenumber;
    }
}

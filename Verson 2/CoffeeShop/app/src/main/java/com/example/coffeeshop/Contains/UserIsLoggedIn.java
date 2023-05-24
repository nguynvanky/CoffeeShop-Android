package com.example.coffeeshop.Contains;

import android.content.Context;

import com.example.coffeeshop.DTO.User;

public class UserIsLoggedIn {
    public static String UsernameLogged;
    public static String Role;

    public  static User user = new User();

    public static int UserIdLogged;
    public static  boolean isLogin;
    public static void signOut()
    {
        Context ctx;
        UsernameLogged = "";
        isLogin  = false;
        UserIdLogged = -1;
        Role = "";
        user= null;
    }

}

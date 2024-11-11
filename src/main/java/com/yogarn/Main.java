package com.yogarn;

import java.util.ArrayList;

import com.yogarn.model.User;
import com.yogarn.service.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        int testUserId = 1;

        User user = userService.fetchUserById(testUserId);

        if (user != null) {
            System.out.println("User ID: " + user.getId());
            System.out.println("User Name: " + user.getName());
            System.out.println("User Email: " + user.getEmail());
            System.out.println("User Password: " + user.getPassword());
        }
    }
}
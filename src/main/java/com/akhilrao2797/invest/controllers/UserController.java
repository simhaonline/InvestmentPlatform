package com.akhilrao2797.invest.controllers;

import com.akhilrao2797.invest.models.User;
import com.akhilrao2797.invest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user('{userId}')")
    @RolesAllowed("USER")
    public ResponseEntity<User> getUserInfo(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.postUser(user));
    }

    @DeleteMapping("/user('{userId}')")
    @RolesAllowed("USER")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        userService.deleteUserById(userId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/user")
    @RolesAllowed("USER")
    public ResponseEntity<User> updateUserInfo(@RequestBody User user){
        return ResponseEntity.ok().build();
    }
}

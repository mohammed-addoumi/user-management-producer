package com.usermanagement.usermanagementproducer.controllers;


import com.usermanagement.usermanagementproducer.models.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping()
    public ResponseEntity<List<String>> getAllUsers(){
        List<String> users = List.of("simo", "test", "kafka");
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){
        log.info("user created with the following infos , username : {} and password : {}"
                ,user.getUserName()
                ,user.getPassword());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}

package com.usermanagement.usermanagementproducer.controllers;


import com.usermanagement.usermanagementproducer.models.User;
import com.usermanagement.usermanagementproducer.producers.UserEventProducer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.usermanagement.usermanagementproducer.mappers.UserEventMapper.mapUserToUserEvent;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserEventProducer userEventProducer;

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
        userEventProducer.sendUserEvent(mapUserToUserEvent(user));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}

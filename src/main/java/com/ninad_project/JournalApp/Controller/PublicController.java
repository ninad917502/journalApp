package com.ninad_project.JournalApp.Controller;

import com.ninad_project.JournalApp.Entity.User;
import com.ninad_project.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){


        userService.saveNewUser(user);
        return new ResponseEntity<>(user , HttpStatus.CREATED);

    }
}

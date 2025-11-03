package com.ninad_project.JournalApp.Controller;

import com.ninad_project.JournalApp.ApiResponce.WheatherResponce;
import com.ninad_project.JournalApp.Entity.User;
import com.ninad_project.JournalApp.Service.UserService;
import com.ninad_project.JournalApp.Service.WheatherService;
import com.ninad_project.JournalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WheatherService wheatherService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PutMapping
    public ResponseEntity<User> updateuser(@RequestBody  User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User UserInDB = userService.findByUsername(userName);
        if(UserInDB != null) {
            UserInDB.setUsername(user.getUsername());
            UserInDB.setPassword(user.getPassword());
            userService.saveNewUser(UserInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WheatherResponce wheatherResponce = wheatherService.getWheather("Mumbai");
        String greeting = "";
        if (wheatherResponce != null){
            greeting = (", Weather feels like "+ wheatherResponce.getCurrent().getFeelslike());
        }
        return new ResponseEntity<>("Hii " + authentication.getName()+ greeting, HttpStatus.OK);
    }

}

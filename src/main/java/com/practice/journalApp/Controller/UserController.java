package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Services.JournalEntryService;
import com.practice.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get-all")
    public List<User> getAll(){
        return userService.getAll();
    }
    @PostMapping("/create-user")
    public ResponseEntity<?> submitEntry(@RequestBody User newUser){
        try {
            userService.saveEntry(newUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        }

    @PutMapping
    public ResponseEntity<User> updateById(@RequestBody User updatedUser ){
        User oldUser =  userService.getByuserName(updatedUser.getUserName());
        if(oldUser != null){
            oldUser.setUserName(updatedUser.getUserName());
            oldUser.setPassword(updatedUser.getPassword());
            userService.saveEntry(oldUser);
            return new ResponseEntity<User>(oldUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

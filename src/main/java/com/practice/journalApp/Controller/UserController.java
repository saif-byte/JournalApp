package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Services.JournalEntryService;
import com.practice.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PutMapping
    public ResponseEntity<User> updateById(@RequestBody User updatedUser ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User oldUser =  userService.getByuserName(userName);
        oldUser.setUserName(updatedUser.getUserName());
        oldUser.setPassword(updatedUser.getPassword());
        userService.saveNewUser(oldUser);
        return new ResponseEntity<User>(HttpStatus.OK);


    }

    @DeleteMapping
    public ResponseEntity<?> delete(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =  userService.getByuserName(userName);
        userService.deletebyUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

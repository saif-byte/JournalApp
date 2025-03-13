package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Repository.UserRepo;
import com.practice.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("get-all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userRepo.findAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

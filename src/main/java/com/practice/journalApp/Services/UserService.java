package com.practice.journalApp.Services;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Repository.JournalEntryRepo;
import com.practice.journalApp.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getByuserName(String userName){
        return userRepository.findByuserName(userName);
    }

    public void deletebyId(String id){
        userRepository.deleteById(id);
    }
}

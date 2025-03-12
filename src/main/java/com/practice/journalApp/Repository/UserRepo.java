package com.practice.journalApp.Repository;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    public User findByuserName(String userName);

    public void deleteByUserName(String username);
}

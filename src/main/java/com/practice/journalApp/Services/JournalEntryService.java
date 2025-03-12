package com.practice.journalApp.Services;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.getByuserName(userName);
        JournalEntry saved= journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        JournalEntry saved= journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(String id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deletebyId(String id, String userName){
        User user = userService.getByuserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id) );
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);

    }
}

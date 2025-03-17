package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Entities.User;
import com.practice.journalApp.Services.JournalEntryService;
import com.practice.journalApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;
    @GetMapping("/get-all")
    public List<JournalEntry> getAll(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByuserName(userName);
        return user.getJournalEntries();
    }
    @PostMapping("/submit-entry")
    public ResponseEntity<?> submitEntry(@RequestBody JournalEntry newEntry){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(newEntry , userName);
            return new ResponseEntity<>(HttpStatus.CREATED);

//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//        }
        }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String id){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByuserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> byId = journalEntryService.getById(id);
            return new ResponseEntity<JournalEntry>(byId.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("update-by-id/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String id , @RequestBody JournalEntry updatedEntry ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByuserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
            JournalEntry oldEntry = collect.get(0);
            if (updatedEntry.getContent() != null && !updatedEntry.getContent().equals(oldEntry.getContent())) {
                oldEntry.setContent(updatedEntry.getContent());
            }
            if (updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals(oldEntry.getTitle())) {
                oldEntry.setTitle(updatedEntry.getTitle());
            }
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id ){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByuserName(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if(!collect.isEmpty()) {
        journalEntryService.deletebyId(id , userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        };
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}

package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @GetMapping("/get-all")
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }
    @PostMapping("/submit-entry")
    public ResponseEntity<?> submitEntry(@RequestBody JournalEntry newEntry){
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(newEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
        }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<JournalEntry> getById(@PathVariable String id){
        Optional<JournalEntry> byId = journalEntryService.getById(id);
        if(byId.isPresent()){
            return new ResponseEntity<JournalEntry>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("update-by-id/{id}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable String id , @RequestBody JournalEntry updatedEntry ){
        JournalEntry oldEntry =  journalEntryService.getById(id).orElse(null);
        if(oldEntry != null){
            if (updatedEntry.getContent()!=null && !updatedEntry.getContent().equals(oldEntry.getContent())) {
                oldEntry.setContent(updatedEntry.getContent());
            }

            if (updatedEntry.getTitle()!=null && !updatedEntry.getTitle().equals(oldEntry.getTitle())) {
                oldEntry.setTitle(updatedEntry.getTitle());
            }
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<JournalEntry>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        journalEntryService.deletebyId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

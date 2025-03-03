package com.practice.journalApp.Controller;

import com.practice.journalApp.Entities.JournalEntry;
import com.practice.journalApp.Services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean submitEntry(@RequestBody JournalEntry newEntry){
        newEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(newEntry);
        return true;
    }

    @GetMapping("/get-by-id/{id}")
    public JournalEntry getById(@PathVariable String id){
        return journalEntryService.getById(id).orElse(null);
    }
    @PutMapping("update-by-id/{id}")
    public JournalEntry updateById(@PathVariable String id ,  @RequestBody JournalEntry updatedEntry ){
        JournalEntry oldEntry =  journalEntryService.getById(id).orElse(null);
        if(oldEntry != null){
            if (updatedEntry.getContent()!=null && !updatedEntry.getContent().equals(oldEntry.getContent())) {
                oldEntry.setContent(updatedEntry.getContent());
            }

            if (updatedEntry.getTitle()!=null && !updatedEntry.getTitle().equals(oldEntry.getTitle())) {
                oldEntry.setTitle(updatedEntry.getTitle());
            }
            journalEntryService.saveEntry(oldEntry);
            return oldEntry;
        }
        return null;
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteById(@PathVariable String id){
        journalEntryService.deletebyId(id);
        return true;
    }


}

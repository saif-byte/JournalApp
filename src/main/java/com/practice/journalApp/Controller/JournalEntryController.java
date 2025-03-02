package com.practice.journalApp.Controller;

import com.practice.journalApp.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long, JournalEntry> entries = new HashMap<>();
    @GetMapping("/get-all")
    public ArrayList<JournalEntry> getAll(){
        return new ArrayList<>(entries.values());
    }
    @PostMapping("/submit-entry")
    public boolean submitEntry(@RequestBody JournalEntry newEntry){
        entries.put(newEntry.getId(), newEntry);
        return true;
    }

    @GetMapping("/get-by-id/{id}")
    public JournalEntry getById(@PathVariable Long id){
        return entries.get(id);
    }
    @PutMapping("update-by-id/{id}")
    public JournalEntry updateById(@PathVariable Long id ,  @RequestBody JournalEntry updatedEntry ){
        entries.put(id , updatedEntry );
        return entries.get(id);
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteById(@PathVariable Long id){
        entries.remove(id);
        return true;
    }


}

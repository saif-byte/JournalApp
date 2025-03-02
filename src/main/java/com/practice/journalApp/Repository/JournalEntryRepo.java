package com.practice.journalApp.Repository;

import com.practice.journalApp.Entities.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry , String> {
}

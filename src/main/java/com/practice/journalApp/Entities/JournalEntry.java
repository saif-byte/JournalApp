package com.practice.journalApp.Entities;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class JournalEntry {
    @Id
    private String id ;

    @NonNull
    private String title;
    private String content;

    private LocalDateTime date;

}

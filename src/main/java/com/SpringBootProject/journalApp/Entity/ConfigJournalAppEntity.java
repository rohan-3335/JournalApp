package com.SpringBootProject.journalApp.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journalApp")
@Data
public class ConfigJournalAppEntity {

    private String key;
    private String value;
}

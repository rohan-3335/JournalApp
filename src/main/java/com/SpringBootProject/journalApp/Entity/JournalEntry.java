package com.SpringBootProject.journalApp.Entity;

import com.SpringBootProject.journalApp.enums.Sentiment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDateTime;

@Getter
@Setter
@Data
@Document(collection = "journalEntry")
public class JournalEntry {

    @Id
    private ObjectId id;
    private  String title;
    @JsonProperty("content")
     private String Content;
    private LocalDateTime date;

    @Field("sentiment")
    private Sentiment sentiment;  // Stored as a String in MongoDB

}

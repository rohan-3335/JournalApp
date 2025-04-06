package com.SpringBootProject.journalApp.repository;

import com.SpringBootProject.journalApp.Entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


    Optional<JournalEntry> findById(String id);
}

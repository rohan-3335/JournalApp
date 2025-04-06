package com.SpringBootProject.journalApp.repository;

import com.SpringBootProject.journalApp.Entity.ConfigJournalAppEntity;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigJournalRepository extends MongoRepository<ConfigJournalAppEntity,ObjectId>{



}

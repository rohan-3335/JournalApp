package com.SpringBootProject.journalApp.repository;

import com.SpringBootProject.journalApp.Entity.ConfigJournalAppEntity;
import com.SpringBootProject.journalApp.Entity.JournalEntry;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository  extends MongoRepository<UserEntity,ObjectId>{


    UserEntity findByUsername(String username);
//
//    void deleteByName(String name);

    void deleteByUsername(String username);
//
//    void deleteByName(String username);

}

package com.SpringBootProject.journalApp.services;

import com.SpringBootProject.journalApp.Entity.JournalEntry;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    public UserService userService;

    @Transactional
    public void saveEntry(JournalEntry entry,String username) {
        UserEntity user = userService.findbyusername(username);

        System.out.println("✅ Entry received successfully: " + entry);
            JournalEntry saved =journalEntryRepository.save(entry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
    }

    public void saveEntry(JournalEntry entry) {
        journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getall(){
       return journalEntryRepository.findAll();
    }

   public Optional<JournalEntry> findByid(String id){
        return journalEntryRepository.findById(id);
   }

    @Transactional
    public void deleteEntry(ObjectId id,String username){
        UserEntity user = userService.findbyusername(username);
        journalEntryRepository.deleteById(id);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
    }
}

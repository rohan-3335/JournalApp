package com.SpringBootProject.journalApp.Controller;

import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.SpringBootProject.journalApp.Entity.JournalEntry;
import com.SpringBootProject.journalApp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/journal")
public class JournelEntryControllerV2 {
    private Map<Integer, JournalEntry> journalEntries = new HashMap<>();
    @Autowired
    public JournalEntryService journalEntryService;
    @Autowired
    public UserService userService;

//
//    public ArrayList<JournalEntry> getall(){
//        return new ArrayList<>(journalEntries.values());
//    }

    @GetMapping()
    public ResponseEntity<?> getallJournalEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findbyusername(username);
        List<JournalEntry> all = user.getJournalEntries();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    //
    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntry(@PathVariable String myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity user = userService.findbyusername(username);
        boolean exists = user.getJournalEntries().stream()
                .anyMatch(entry -> entry.getId().toString().equals(myId));

        if (exists) {  // If entry exists, fetch it and return
            Optional<JournalEntry> j = journalEntryService.findByid(myId);
            return new ResponseEntity<>(j, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping()
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findbyusername(username);

        Optional<JournalEntry> j = user.getJournalEntries().stream()
                .filter(entry -> entry.getId().equals(myId))
                .findFirst();
        if (j.isPresent()) {
            journalEntryService.deleteEntry(myId, username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable String id, @RequestBody JournalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userService.findbyusername(username);

        Optional<JournalEntry> j = user.getJournalEntries().stream()
                .filter(entry -> entry.getId().toString().equals(id)) // Convert ObjectId to String
                .findFirst();
        if (j.isPresent()) {
            JournalEntry existingEntry = j.get();  // Get the actual JournalEntry object

            // ✅ Update only non-null and non-empty fields
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                existingEntry.setTitle(newEntry.getTitle());
            }
            if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
                existingEntry.setContent(newEntry.getContent());
            }
            if (newEntry.getDate() != null) {
                existingEntry.setDate(newEntry.getDate());
            }

            // ✅ Save and Return Updated Entry
            journalEntryService.saveEntry(existingEntry);
            return ResponseEntity.ok(existingEntry);

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}




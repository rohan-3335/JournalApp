package com.SpringBootProject.journalApp.Scheduler;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.SpringBootProject.journalApp.Entity.JournalEntry;
import com.SpringBootProject.journalApp.Entity.UserEntity;
import com.SpringBootProject.journalApp.cache.AppCache;
import com.SpringBootProject.journalApp.enums.Sentiment;
import com.SpringBootProject.journalApp.repository.UserRepositoryImpl;
import com.SpringBootProject.journalApp.services.EmailService;
import com.SpringBootProject.journalApp.services.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {


    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

//
//    @Scheduled(cron = "0 0 21 * * SUN")
    public void fetchUserAndSendEmail(){
        List<UserEntity> users = userRepository.getUserforSA();
        for(UserEntity user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> filteredEntries = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(JournalEntry::getSentiment)  // ✅ Use Enum directly
                    .collect(Collectors.toList());


            // Count occurrences of each sentiment
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : filteredEntries) {
                if (sentiment != null) {
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
                }
            }

            // Find the most frequent sentiment
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            // Send email if a valid sentiment is found
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(
                        user.getEmail(),
                        "Sentiment for last 7 days",
                        "Hii" + user.getUsername() +",\nYour most frequent sentiment in the last 7 days: " + mostFrequentSentiment.toString()
                );
            }


        }
    }
    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 */15 * * * *")
    public void setAppCache(){
        appCache.init();
        System.out.println("Initialized the reset appcache");
    }




}

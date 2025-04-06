package com.SpringBootProject.journalApp.Services;

import com.SpringBootProject.journalApp.Scheduler.UserSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SentimentAnalysisEmainTest {
    @Autowired
    private UserSchedular userSchedular;


    @Test
    public void sentimentAnalysisTest(){
        userSchedular.fetchUserAndSendEmail();
    }
}

package com.ninad_project.JournalApp.Scheduler;

import com.ninad_project.JournalApp.Entity.JournalEntry;
import com.ninad_project.JournalApp.Entity.User;
import com.ninad_project.JournalApp.Service.EmailService;
import com.ninad_project.JournalApp.Service.SentimentAnalysisService;
import com.ninad_project.JournalApp.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

   @Scheduled(cron = "0 0 9 * * SUN")     //It Runs self automatically
    public void FetchUserAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for( User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filtersEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join("", filtersEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.SendMail(user.getEmail(), "sentiment for 7 days", sentiment);
        }
    }
}

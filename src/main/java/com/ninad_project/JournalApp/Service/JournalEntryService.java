package com.ninad_project.JournalApp.Service;

import com.ninad_project.JournalApp.Entity.JournalEntry;
import com.ninad_project.JournalApp.Entity.User;
import com.ninad_project.JournalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);

        }catch (Exception e){
            log.error("Error", e);
            throw new RuntimeException("An error occurred while saving the entry", e);
        }

    }


    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById (ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean Removed = false;
        try {
        User user = userService.findByUsername(userName);
        Removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(Removed) {
            userService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        }

        }catch(Exception e){
        throw new RuntimeException("Id Not Found",e);
        }
        return Removed;
    }

}

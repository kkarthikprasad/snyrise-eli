package com.snyrise.eli.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.snyrise.eli.entity.JournalEntity;
import com.snyrise.eli.model.Journal;
import com.snyrise.eli.service.JournalsService;

@CrossOrigin("*")
@RestController
public class JournalsController {

  @Autowired
  JournalsService service;

  @GetMapping("/journals")
  public ResponseEntity<List<JournalEntity>> getAllJournals() {
    return new ResponseEntity<>(service.getAllJournals(), HttpStatus.OK);
  }

  @GetMapping("/journals/{journalName}")
  public ResponseEntity<JournalEntity> getJournal(
      @PathVariable(value = "journalName") String journalName) {
    return new ResponseEntity<>(service.getJournalByName(journalName), HttpStatus.OK);
  }

  @PostMapping("/journals")
  public ResponseEntity<JournalEntity> createJournal(@RequestBody Journal journal) {
    return new ResponseEntity<>(service.createJournal(journal), HttpStatus.CREATED);
  }

  @PutMapping("/journals/{journalName}")
  public ResponseEntity<JournalEntity> updateJournal(
      @PathVariable(name = "journalName", required = true) String journalName,
      @RequestBody Journal journal) {
    return new ResponseEntity<>(service.updateJournal(journalName, journal), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/journals/{journalName}")
  public ResponseEntity<HttpStatus> deleteJournal(
      @PathVariable(name = "journalName", required = true) String journalName) {
    service.deleteJournal(journalName);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}

package com.snyrise.eli.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.snyrise.eli.entity.JournalEntity;

public interface JournalsRepository extends MongoRepository<JournalEntity, String> {

  public JournalEntity findByName(String journalName);

}

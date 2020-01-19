package com.coppolab.first_homework.interfaces;

import com.coppolab.first_homework.entity.Record;
import com.coppolab.first_homework.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RecordRepository extends CrudRepository<Record,Integer> {
    Optional<Record> findRecordByAuthor(User user);
}

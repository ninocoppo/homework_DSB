package com.coppolab.first_homework.interfaces;

import com.coppolab.first_homework.entity.Record;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<Record,Integer> {
}

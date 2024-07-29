package com.TrainingManagement.DailyUpdates.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.TrainingManagement.DailyUpdates.entity.DailyUpdate;

@Repository
public interface DailyUpdateRepository extends MongoRepository<DailyUpdate, String> {
    DailyUpdate findByEmployeeIdAndDate(String employeeId, LocalDate date);
    List<DailyUpdate> findByEmployeeId(String employeeId);
    DailyUpdate getDailyUpdateByEmployeeIdAndDate(String employeeId, LocalDate date);
}

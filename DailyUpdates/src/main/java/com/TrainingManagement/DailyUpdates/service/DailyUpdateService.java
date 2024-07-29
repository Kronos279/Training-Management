package com.TrainingManagement.DailyUpdates.service;

import java.time.LocalDate;
import java.util.List;

import com.TrainingManagement.DailyUpdates.entity.DailyUpdate;

public interface DailyUpdateService {
    DailyUpdate saveDailyUpdate(String employeeId, String updateText);
    List<DailyUpdate> getDailyUpdatesByEmployeeId(String employeeId);
    void markMissedUpdates();
    DailyUpdate getDailyUpdateByEmployeeIdAndDate(String employeeId, LocalDate date);
}
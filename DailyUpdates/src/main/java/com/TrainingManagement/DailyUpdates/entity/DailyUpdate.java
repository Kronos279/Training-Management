package com.TrainingManagement.DailyUpdates.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dailyUpdates")
public class DailyUpdate {

    @Id
    private String id;
    private String employeeId;
    private LocalDate date;
    private String updateText;
    private String status;
}

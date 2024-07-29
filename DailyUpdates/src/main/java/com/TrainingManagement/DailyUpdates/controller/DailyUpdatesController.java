package com.TrainingManagement.DailyUpdates.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TrainingManagement.DailyUpdates.entity.DailyUpdate;
import com.TrainingManagement.DailyUpdates.service.DailyUpdateService;

@RestController
@RequestMapping("/dailyupdates")
@CrossOrigin
public class DailyUpdatesController {
	
	 @Autowired
	    private DailyUpdateService dailyUpdateService;

	    @PostMapping("/{employeeId}")
	    public ResponseEntity<DailyUpdate> saveDailyUpdate(@PathVariable String employeeId, @RequestBody String updateText) {
	        DailyUpdate dailyUpdate = dailyUpdateService.saveDailyUpdate(employeeId, updateText);
	        return ResponseEntity.ok(dailyUpdate);
	    }

	    @GetMapping("/{employeeId}/{date}")
	    public ResponseEntity<DailyUpdate> getDailyUpdateByEmployeeIdAndDate(
	            @PathVariable String employeeId, @PathVariable String date) {
	        LocalDate localDate = LocalDate.parse(date);
	        DailyUpdate dailyUpdate = dailyUpdateService.getDailyUpdateByEmployeeIdAndDate(employeeId, localDate);
	        return ResponseEntity.ok(dailyUpdate);
	    }

	    @GetMapping("/{employeeId}")
	    public ResponseEntity<List<DailyUpdate>> getDailyUpdatesByEmployeeId(@PathVariable String employeeId) {
	        List<DailyUpdate> dailyUpdates = dailyUpdateService.getDailyUpdatesByEmployeeId(employeeId);
	        return ResponseEntity.ok(dailyUpdates);
	    }

}

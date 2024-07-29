package com.TrainingManagement.BatchDetails.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TrainingManagement.BatchDetails.entity.Announcement;
import com.TrainingManagement.BatchDetails.entity.Batch;
import com.TrainingManagement.BatchDetails.service.BatchService;

@RestController
@RequestMapping("/batch")
@CrossOrigin
public class BatchController {

	
	@Autowired
    private BatchService batchService;

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<Batch>> getBatchesByMentorId(@PathVariable String mentorId) {
        List<Batch> batches = batchService.getBatchesByMentorId(mentorId);
        return new ResponseEntity<>(batches, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches(){
		return new ResponseEntity<>(batchService.getAllBatches(), HttpStatus.OK);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<Batch> getBatchById(@PathVariable String batchId) {
        Optional<Batch> batch = batchService.getBatchById(batchId);
        if(batch.isPresent()) {
        	return new ResponseEntity<>(batch.get(), HttpStatus.OK);
        }
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addBatch")
    public ResponseEntity<Batch> saveBatch(@RequestBody Batch batch) {
        Batch savedBatch = batchService.saveBatch(batch);
        return new ResponseEntity<>(savedBatch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<Void> deleteBatch(@PathVariable String batchId) {
        batchService.deleteBatch(batchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{batchId}/addMentee")
    public ResponseEntity<Batch> addMenteeToBatch(
            @PathVariable String batchId, 
            @RequestParam String mentorId, 
            @RequestParam String menteeId) {
        try {
            Batch updatedBatch = batchService.addMenteeToBatch(batchId, mentorId, menteeId);
            return new ResponseEntity<>(updatedBatch, HttpStatus.OK);
        }catch (RuntimeException e) {
            if (e.getMessage().equals("Unauthorized action")) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else if (e.getMessage().equals("Batch not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{batchId}")
    public ResponseEntity<Batch> updateBatch( @PathVariable String batchId, @RequestBody Batch updatedBatch) {
    	return new ResponseEntity<Batch>(batchService.updateBatch(batchId, updatedBatch),HttpStatus.OK);
    }
    
    @GetMapping("/employeeCount")
    public ResponseEntity<Integer> getEmployeeCountInBatch(@RequestParam String batchId){
    	return new ResponseEntity<>(batchService.getEmployeeCountInBatch(batchId),HttpStatus.OK);
    }
    
    @PostMapping("/announcement/{batchId}")
    public ResponseEntity<Batch> addAnnouncement(@PathVariable String batchId, @RequestBody String announcement) {
    	return new ResponseEntity<>(batchService.addAnnouncementToBatch(batchId, announcement),HttpStatus.CREATED);
    }
    
    @GetMapping("/announcement")
    public ResponseEntity<List<Announcement>> getAnnouncement(@RequestParam String batchId){
    	return new ResponseEntity<>(batchService.getAnnouncement(batchId),HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteAnnouncement")
    public ResponseEntity<?> deleteAnnouncement(@RequestParam String batchId, @RequestParam String announcementId){
    	return new ResponseEntity<>(batchService.deleteAnnouncement(batchId, announcementId), HttpStatus.ACCEPTED);
    }
    
}

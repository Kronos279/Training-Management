package com.TrainingManagement.BatchDetails.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.TrainingManagement.BatchDetails.entity.Announcement;
import com.TrainingManagement.BatchDetails.entity.Batch;

public interface BatchService {
	
	List<Batch> getBatchesByMentorId(String mentorId);
    Optional<Batch> getBatchById(String batchId);
    Batch saveBatch(Batch batch);
    void deleteBatch(String batchId);
    Batch addMenteeToBatch(String batchId, String mentorId, String menteeId);
    public Batch updateBatch(String batchId,Batch updatedBatch);
    public int getEmployeeCountInBatch(String batchId);
    public Batch addAnnouncementToBatch(String batchId, String announcement);
    public List<Announcement> getAnnouncement(String batchId);
    public String deleteAnnouncement(String batchId,String announcementId);
    public List<Batch> getAllBatches();

}

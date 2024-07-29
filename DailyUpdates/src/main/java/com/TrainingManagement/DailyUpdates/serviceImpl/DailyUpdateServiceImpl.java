package com.TrainingManagement.DailyUpdates.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TrainingManagement.DailyUpdates.Client.EmployeeDetailsClient;
import com.TrainingManagement.DailyUpdates.entity.DailyUpdate;
import com.TrainingManagement.DailyUpdates.entity.Employee;
import com.TrainingManagement.DailyUpdates.repository.DailyUpdateRepository;
import com.TrainingManagement.DailyUpdates.service.DailyUpdateService;

@Service
public class DailyUpdateServiceImpl implements DailyUpdateService {

	@Autowired
	private DailyUpdateRepository dailyUpdateRepository;

	@Autowired
	private EmployeeDetailsClient empClient;

	@Override
	public DailyUpdate saveDailyUpdate(String employeeId, String updateText) {
		LocalDate today = LocalDate.now();
		DailyUpdate existingUpdate = dailyUpdateRepository.findByEmployeeIdAndDate(employeeId, today);
		DailyUpdate dailyUpdate;
		if (existingUpdate == null) {
			dailyUpdate = new DailyUpdate();
			dailyUpdate.setEmployeeId(employeeId);
			dailyUpdate.setDate(today);
		} else {
			dailyUpdate = existingUpdate;
		}
		dailyUpdate.setUpdateText(updateText);
		dailyUpdate.setStatus("COMPLETED");

		return dailyUpdateRepository.save(dailyUpdate);
	}

	@Override
	public DailyUpdate getDailyUpdateByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return dailyUpdateRepository.findByEmployeeIdAndDate(employeeId, date);
    }

	@Override
	public List<DailyUpdate> getDailyUpdatesByEmployeeId(String employeeId) {
		return dailyUpdateRepository.findByEmployeeId(employeeId);
	}

	@Override
	public void markMissedUpdates() {
		LocalDate yesterday = LocalDate.now().minusDays(1);
		List<String> allEmployees = fetchAllEmployeeIds();
		for (String employeeId : allEmployees) {
			 DailyUpdate update = dailyUpdateRepository.findByEmployeeIdAndDate(employeeId, yesterday);
			 if (update == null) {
	                DailyUpdate missedUpdate = new DailyUpdate();
	                missedUpdate.setEmployeeId(employeeId);
	                missedUpdate.setDate(yesterday);
	                missedUpdate.setUpdateText("Missed");
	                missedUpdate.setStatus("MISSED");
	                dailyUpdateRepository.save(missedUpdate);
	            }
		}

	}

	List<String> fetchAllEmployeeIds() {

		List<String> employeeIds = new ArrayList<>();
		List<Employee> emps = empClient.getEmployees();
		if (!emps.isEmpty()) {
			for (Employee emp : emps) {
				employeeIds.add(emp.getEmployeeId());
			}
		}
		return employeeIds;

	}

}

package com.TrainingManagement.Auth.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TrainingManagement.Auth.entity.UserCredential;

public interface UserCredentialRepo  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByEmployeeId(String employee_id);
}
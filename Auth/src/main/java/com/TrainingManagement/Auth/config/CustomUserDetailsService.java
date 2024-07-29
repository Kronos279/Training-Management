package com.TrainingManagement.Auth.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.TrainingManagement.Auth.entity.UserCredential;
import com.TrainingManagement.Auth.repo.UserCredentialRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepo repository;

    @Override
    public UserDetails loadUserByUsername(String employee_id) throws UsernameNotFoundException {
        Optional<UserCredential> credential = repository.findByEmployeeId(employee_id);
        return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + employee_id));
    }
}
package com.TrainingManagement.Auth.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.TrainingManagement.Auth.entity.UserCredential;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

    private String employee_id;
    private String password;

    public CustomUserDetails(UserCredential userCredential) {
        this.employee_id = userCredential.getEmployeeId();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getUsername() {
		return null;
	}
}
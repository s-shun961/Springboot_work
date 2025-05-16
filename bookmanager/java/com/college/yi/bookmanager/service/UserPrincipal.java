package com.college.yi.bookmanager.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.college.yi.bookmanager.model.User;

public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override 
    public String getUsername() {
    	return user.getUsername(); 
    }
    
    @Override 
    public String getPassword() {
    	return user.getPassword(); 
    }
    
    @Override 
    public boolean isEnabled() {
    	return user.isEnabled(); 
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
}

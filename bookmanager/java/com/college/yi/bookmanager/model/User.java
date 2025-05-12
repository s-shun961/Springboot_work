package com.college.yi.bookmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	
    private Integer id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;

}

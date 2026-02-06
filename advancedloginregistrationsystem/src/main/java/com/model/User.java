package com.model;

import java.sql.Timestamp;

public class User {

    private int id;
    private String username;
    private String email;
    private String mobileNumber;
    private String passwordHash;
    private Timestamp createdAt;
    private String role;
    // ✅ No-arg constructor (required for frameworks & JDBC)
    public User() {
    }

    // ✅ Constructor for registration
    

    // ---- Getters & Setters ----

    public int getId() {
        return id;
    }
    
    public User(String username, String email, String mobileNumber, String passwordHash) {
        this.username = username;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.passwordHash = passwordHash;
        this.role = "USER";
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }
    
    public User(int id, String username, String email, String mobileNumber, String passwordHash, Timestamp createdAt,
			String role) {
		
		this.id = id;
		this.username = username;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.passwordHash = passwordHash;
		this.createdAt = createdAt;
		this.role = role;
	}
    
    
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}



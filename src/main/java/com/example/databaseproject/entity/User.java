package com.example.databaseproject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	//@Length(max=10)
	private String firstName;
	@NotNull
//	@Length(max=10)
	private String lastName;
	@NotNull
	@Email
	@Column(unique=true)
	private String emailId;
	@NotNull
	//@Length(min=10,max=10)
	private String phoneNumber;
	@NotNull
	//@Length(min=6)
	@Min(value = 6,message = "password min must be 6")
	private String password;
	
	@NotNull
	private String registerStamp;
	@NotNull
	private String updateStamp;
	@NotNull
	private boolean isVerified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRegisterStamp() {
		return registerStamp;
	}
	public void setRegisterStamp(String registerStamp) {
		this.registerStamp = registerStamp;
	}
	public String getUpdateStamp() {
		return updateStamp;
	}
	public void setUpdateStamp(String updateStamp) {
		this.updateStamp = updateStamp;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", registerStamp=" + registerStamp
				+ ", updateStamp=" + updateStamp + ", isVerified=" + isVerified + "]";
	}

	/*
	 * public User(Long id, @NotNull @Length(max = 10) String
	 * firstName, @NotNull @Length(max = 10) String lastName,
	 * 
	 * @NotNull @Email String emailId, @NotNull @Length(min = 10, max = 10) String
	 * phoneNumber,
	 * 
	 * @NotNull @Length(min = 6) String password, @NotNull String
	 * registerStamp, @NotNull String updateStamp,
	 * 
	 * @NotNull boolean isVerified) { super(); this.id = id; this.firstName =
	 * firstName; this.lastName = lastName; this.emailId = emailId; this.phoneNumber
	 * = phoneNumber; this.password = password; this.registerStamp = registerStamp;
	 * this.updateStamp = updateStamp; this.isVerified = isVerified; }
	 */
	public User() {
		
	}
}

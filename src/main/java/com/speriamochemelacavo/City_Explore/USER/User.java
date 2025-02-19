package com.speriamochemelacavo.City_Explore.USER;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
	public String surname;
	public String username;
	public String email;
	public String password;
	public Role role;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() { return role; }
	public void setRole(Role role) {this.role = role;}

	 @Override
	public String toString() {
		 return "User: " + username;
	 }
}

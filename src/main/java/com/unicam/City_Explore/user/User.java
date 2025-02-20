package com.unicam.City_Explore.user;

import jakarta.persistence.*;

import java.util.List;

import com.unicam.City_Explore.evento.Event;
import com.unicam.City_Explore.notifica.Notification;

@Entity
@Table(name = "users")
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
	@OneToMany
	private List<Notification> notificationList;
	@OneToMany
	private List<Event> eventList;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
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

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	@Override
	public String toString() {
		 return "User: " +
				 "\n" + name +
				 ",\n" + surname +
				 ",\n"+ username+
				 ",\n" + email +
				 ",\n" + role;
	 }
}

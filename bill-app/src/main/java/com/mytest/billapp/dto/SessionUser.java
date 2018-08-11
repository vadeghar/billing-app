package com.mytest.billapp.dto;

import java.util.List;

public class SessionUser {
	
	public SessionUser() {}
	public SessionUser(String userName, String email, Boolean active) {
		this.userName = userName;
		this.email = email;
		this.active = active;
	}
	private String userName;
	private String email;
	private Boolean active;
	private String role;
	private List<String> allRoles;
	private List<MenuItem> userMenuItems;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<MenuItem> getUserMenuItems() {
		return userMenuItems;
	}
	public void setUserMenuItems(List<MenuItem> userMenuItems) {
		this.userMenuItems = userMenuItems;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getAllRoles() {
		return allRoles;
	}
	public void setAllRoles(List<String> allRoles) {
		this.allRoles = allRoles;
	}
}

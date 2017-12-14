package com.lognsys.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * Description: User class is Value Object which populates client side object.
 * This takes care of client side validation.
 * 
 * Change LOG :
 * 
 * 1) PJD - 04/05/17 Added Fields String role, String group
 * 
 * @author pdoshi
 */
public class Users {

	private int id;
	private String password = "";

	private String username = "";
	private String realname = "";
	private String phone = "";
	private String birthdate = null;
	private boolean enabled = false;
	private boolean notification = false;

	private String address = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";
	private String firstname = "";
	private String lastname = "";
	private String role = "";
	private String dailylogs = "";
	private String bu = "";
	
	public Users() {
		//no-arg constructor
	}

	public Users(int id, String username, String realname, String phone, String birthdate, boolean enabled,
			boolean notification, String address, String city, String state, String zipcode, String firstname,
			String lastname) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.phone = phone;
		this.birthdate = birthdate;
		this.enabled = enabled;
		this.notification = notification;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Users(int id, String username, String realname, String phone, String birthdate, boolean enabled,
			boolean notification, String address, String city, String state, String zipcode, String firstname,
			String lastname, String role, String dailylogs, String bu) {
		super();
		this.id = id;
		this.username = username;
		this.realname = realname;
		this.phone = phone;
		this.birthdate = birthdate;
		this.enabled = enabled;
		this.notification = notification;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.dailylogs = dailylogs;
		this.bu = bu;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isNotification() {
		return notification;
	}

	public void setNotification(boolean notification) {
		this.notification = notification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDailylogs() {
		return dailylogs;
	}

	public void setDailylogs(String dailylogs) {
		this.dailylogs = dailylogs;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", realname=" + realname + ", phone=" + phone
				+ ", birthdate=" + birthdate + ", enabled=" + enabled + ", notification=" + notification + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", role=" + role + ", dailylogs=" + dailylogs + ", bu=" + bu + "]";
	}

}

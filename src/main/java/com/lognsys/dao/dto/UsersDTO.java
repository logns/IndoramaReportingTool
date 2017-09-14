package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Database object
 * 
 * @author pdoshi
 *
 */

@Document(collection = "users")
public class UsersDTO {
	@Id
	private int id;
	private String realname = "";
	private String username = "";
	private String phone = "";
	private String birthdate = null;
	private boolean enabled = false;
	private boolean notification = false;

	private String address = "";
	private String city = "";
	private String state = "";
	private String zipcode = "";

	public UsersDTO() {
		super();
	}

	public UsersDTO(int id, String realname, String username, String phone, String address,
			String birthdate, boolean enabled, boolean notification,  String city,
			String state, String zipcode) {
		super();
		this.id = id;
		this.realname = realname;
		this.username = username;
		this.phone = phone;
		this.birthdate = birthdate;
		this.enabled = enabled;
		this.notification = notification;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public void setRealname(String firstname, String lastname) {

		firstname = firstname != null ? firstname : "";
		lastname = lastname != null ? lastname : "";

		this.realname = firstname + " " + lastname;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UsersDTO [id=" + id + ", realname=" + realname + ", username=" + username + ", phone=" + phone
				+ ", birthdate=" + birthdate + ", enabled=" + enabled + ", notification=" + notification + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}


}

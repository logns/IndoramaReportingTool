package com.lognsys.model;

public class PasswordChangeRequests {

	private String hash_id;
	private String time;

	private String password;

	private int users_id;

	
	/**
	 * @return the hash_id
	 */
	public String getHash_id() {
		return hash_id;
	}
	/**
	 * @param hash_id the hash_id to set
	 */
	public void setHash_id(String hash_id) {
		this.hash_id = hash_id;
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
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the users_id
	 */
	public int getUsers_id() {
		return users_id;
	}
	/**
	 * @param users_id the users_id to set
	 */
	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}
	
	public PasswordChangeRequests(String hash_id, String time, String password, int users_id) {
		super();
		this.hash_id = hash_id;
		this.time = time;
		this.password = password;
		this.users_id = users_id;
	}
	public PasswordChangeRequests() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PasswordChangeRequests [hash_id=" + hash_id + ", time=" + time + ", password=" + password
				+ ", users_id=" + users_id + "]";
	}
	
}

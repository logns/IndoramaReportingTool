package com.lognsys.model;

public class PasswordChangeRequests {

	private String id;
	private String time;
	private int users_id;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	public PasswordChangeRequests(String id, String time, int users_id) {
		super();
		this.id = id;
		this.time = time;
		this.users_id = users_id;
	}
	public PasswordChangeRequests() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PasswordChangeRequests [id=" + id + ", time=" + time + ", users_id=" + users_id + "]";
	}
	
}

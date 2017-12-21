package com.lognsys.dao.dto;

public class PasswordChangeRequestsDTO {


	private String hash_id;
	private String time;
	private int users_id;
	private int no_of_attempts;
	
	/**
	 * @return the id
	 */
	
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
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
	 * @return the no_of_attemps
	 */
	public int getNo_of_attempts() {
		return no_of_attempts;
	}
	/**
	 * @param no_of_attemps the no_of_attemps to set
	 */
	public void setNo_of_attempts(int no_of_attempts) {
		this.no_of_attempts = no_of_attempts;
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

	public PasswordChangeRequestsDTO(String hash_id, String time, int users_id, int no_of_attempts) {
		super();
		this.hash_id = hash_id;
		this.time = time;
		this.users_id = users_id;
		this.no_of_attempts = no_of_attempts;
	}
	public PasswordChangeRequestsDTO() {
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
		return "PasswordChangeRequestsDTO [hash_id=" + hash_id + ", time=" + time + ", users_id=" + users_id + ", no_of_attempts="
				+ no_of_attempts + "]";
	}
}

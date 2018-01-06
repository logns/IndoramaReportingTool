package com.lognsys.model;

public class ResetOldPassword {

	private String oldpassword;
	private String username;
	private String newpassword;
	public ResetOldPassword(String oldpassword, String username, String newpassword) {
		super();
		this.oldpassword = oldpassword;
		this.username = username;
		this.newpassword = newpassword;
	}
	public ResetOldPassword() {
		super();
	}
	/**
	 * @return the oldpassword
	 */
	public String getOldpassword() {
		return oldpassword;
	}
	/**
	 * @param oldpassword the oldpassword to set
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}
	/**
	 * @param newpassword the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResetOldPassword [oldpassword=" + oldpassword + ", username=" + username + ", newpassword="
				+ newpassword + "]";
	}
	
	
}

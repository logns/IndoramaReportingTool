package com.lognsys.model;

public class UpdateAssignedCount {

	String updatedDates;
	String assignedTo;
	public String getUpdatedDates() {
		return updatedDates;
	}
	public void setUpdatedDates(String updatedDates) {
		this.updatedDates = updatedDates;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public UpdateAssignedCount(String updatedDates, String assignedTo) {
		super();
		this.updatedDates = updatedDates;
		this.assignedTo = assignedTo;
	}
	public UpdateAssignedCount() {
		super();
	}
	@Override
	public String toString() {
		return "UpdateAssignedCount [updatedDates=" + updatedDates + ", assignedTo=" + assignedTo + "]";
	}
	
	
}

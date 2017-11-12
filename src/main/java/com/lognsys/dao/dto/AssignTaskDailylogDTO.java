package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.lognsys.model.DailyLog;

public class AssignTaskDailylogDTO {
	@Id
	private int id;

	@DBRef
	private DailyLog dailylogDTO;

	@DBRef
	private AssignTaskDTO assignTaskDTO;

	public AssignTaskDailylogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignTaskDailylogDTO(int id, DailyLog dailylogDTO, AssignTaskDTO assignTaskDTO) {
		super();
		this.id = id;
		this.dailylogDTO = dailylogDTO;
		this.assignTaskDTO = assignTaskDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DailyLog getDailylogDTO() {
		return dailylogDTO;
	}

	public void setDailylogDTO(DailyLog dailylogDTO) {
		this.dailylogDTO = dailylogDTO;
	}

	public AssignTaskDTO getAssignTaskDTO() {
		return assignTaskDTO;
	}

	public void setAssignTaskDTO(AssignTaskDTO assignTaskDTO) {
		this.assignTaskDTO = assignTaskDTO;
	}

	@Override
	public String toString() {
		return "AssignTaskDailylogDTO [id=" + id + ", dailylogDTO=" + dailylogDTO + ", assignTaskDTO=" + assignTaskDTO
				+ "]";
	}

}

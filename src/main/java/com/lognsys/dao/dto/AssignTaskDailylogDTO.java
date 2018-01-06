package com.lognsys.dao.dto;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.lognsys.model.DailyLog;

public class AssignTaskDailylogDTO {
	@Id
	private int id;

	@DBRef
	private DailyLogDTO dailylogDTO;

	@DBRef
	private ArrayList<DailyLogDTO> dailylogDTOs;

	@DBRef
	private AssignTaskDTO assignTaskDTO;

	public AssignTaskDailylogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AssignTaskDailylogDTO(int id, DailyLogDTO dailylogDTO, AssignTaskDTO assignTaskDTO,ArrayList<DailyLogDTO> dailylogDTOs) {
		super();
		this.id = id;
		this.dailylogDTO = dailylogDTO;
		this.assignTaskDTO = assignTaskDTO;
		this.dailylogDTOs = dailylogDTOs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DailyLogDTO getDailylogDTO() {
		return dailylogDTO;
	}

	public void setDailylogDTO(DailyLogDTO dailylogDTO) {
		this.dailylogDTO = dailylogDTO;
	}

	public AssignTaskDTO getAssignTaskDTO() {
		return assignTaskDTO;
	}

	public void setAssignTaskDTO(AssignTaskDTO assignTaskDTO) {
		this.assignTaskDTO = assignTaskDTO;
	}

	
	public ArrayList<DailyLogDTO> getDailyLogDTOs() {
		return dailylogDTOs;
	}

	public void setDailyLogDTOs(ArrayList<DailyLogDTO> dailylogDTOs) {
		this.dailylogDTOs = dailylogDTOs;
	}

	@Override
	public String toString() {
		return "AssignTaskDailylogDTO [id=" + id + ", dailylogDTO=" + dailylogDTO + ", assignTaskDTO=" + assignTaskDTO
				+ "]";
	}

}

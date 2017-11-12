package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.lognsys.model.DailyLog;

public class DailyLogBuDTO {
	@Id
	private int id;

	@DBRef
	private DailyLog dailylogDTO;

	@DBRef
	private BuDTO buDTO;

	public DailyLogBuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DailyLogBuDTO(int id, DailyLog dailylogDTO, BuDTO buDTO) {
		super();
		this.id = id;
		this.dailylogDTO = dailylogDTO;
		this.buDTO = buDTO;
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

	public BuDTO getBuDTO() {
		return buDTO;
	}

	public void setBuDTO(BuDTO buDTO) {
		this.buDTO = buDTO;
	}

	@Override
	public String toString() {
		return "DailyLogBuDTO [id=" + id + ", dailylogDTO=" + dailylogDTO + ", buDTO=" + buDTO + "]";
	}

}

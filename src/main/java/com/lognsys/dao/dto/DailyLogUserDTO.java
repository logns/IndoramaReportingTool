package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.lognsys.model.DailyLog;

public class DailyLogUserDTO {
		@Id
		private int id;

		@DBRef
		private DailyLog dailylogDTO;

		@DBRef
		private UsersDTO user;

		public DailyLogUserDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public DailyLogUserDTO(int id, DailyLog dailylogDTO, UsersDTO user) {
			super();
			this.id = id;
			this.dailylogDTO = dailylogDTO;
			this.user = user;
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

		public UsersDTO getUser() {
			return user;
		}

		public void setUser(UsersDTO user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "DailyLogUserDTO [id=" + id + ", dailylogDTO=" + dailylogDTO + ", user=" + user + "]";
		}

}

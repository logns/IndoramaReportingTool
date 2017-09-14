package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dailylogs")
public class DailyLogDTO {
	@Id
	private int id;
	private double loadmax;
	private double loadmin;
	private double voltmax;
	private double voltmin;
	private double frequencymax;
	private double frequencymin;
	private double pfmax;
	private double pfmin;
	private double powerdip;
	private String remark = "";
	private String machine = "";
	private String description = "";
	private String timefrom = "";
	private String timeto = "";
	private String spareparts = "";
	private String attendby = "";
	private String jobtype = "";
	private String dates = "";
	private String substation = "";
	private String recordtype = "";
	private String status = "";
	private String shift = "";
	
	public DailyLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DailyLogDTO(int id, double loadmax, double loadmin, double voltmax, double voltmin, double frequencymax,
			double frequencymin, double pfmax, double pfmin, double powerdip, String remark, String machine,
			String description, String timefrom, String timeto, String spareparts, String attendby, String jobtype,
			String dates, String recordtype, String status,String substation,String shift) {
		super();
		this.id = id;
		this.loadmax = loadmax;
		this.loadmin = loadmin;
		this.voltmax = voltmax;
		this.voltmin = voltmin;
		this.frequencymax = frequencymax;
		this.frequencymin = frequencymin;
		this.pfmax = pfmax;
		this.pfmin = pfmin;
		this.powerdip = powerdip;
		this.remark = remark;
		this.machine = machine;
		this.description = description;
		this.timefrom = timefrom;
		this.timeto = timeto;
		this.spareparts = spareparts;
		this.attendby = attendby;
		this.jobtype = jobtype;
		this.dates = dates;
		this.recordtype = recordtype;
		this.status = status;
		this.substation = substation;
		this.shift = shift;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getSubstation() {
		return substation;
	}

	public void setSubstation(String substation) {
		this.substation = substation;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getLoadmax() {
		return loadmax;
	}
	public void setLoadmax(double loadmax) {
		this.loadmax = loadmax;
	}
	public double getLoadmin() {
		return loadmin;
	}
	public void setLoadmin(double loadmin) {
		this.loadmin = loadmin;
	}
	public double getVoltmax() {
		return voltmax;
	}
	public void setVoltmax(double voltmax) {
		this.voltmax = voltmax;
	}
	public double getVoltmin() {
		return voltmin;
	}
	public void setVoltmin(double voltmin) {
		this.voltmin = voltmin;
	}
	public double getFrequencymax() {
		return frequencymax;
	}
	public void setFrequencymax(double frequencymax) {
		this.frequencymax = frequencymax;
	}
	public double getFrequencymin() {
		return frequencymin;
	}
	public void setFrequencymin(double frequencymin) {
		this.frequencymin = frequencymin;
	}
	public double getPfmax() {
		return pfmax;
	}
	public void setPfmax(double pfmax) {
		this.pfmax = pfmax;
	}
	public double getPfmin() {
		return pfmin;
	}
	public void setPfmin(double pfmin) {
		this.pfmin = pfmin;
	}
	public double getPowerdip() {
		return powerdip;
	}
	public void setPowerdip(double powerdip) {
		this.powerdip = powerdip;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimefrom() {
		return timefrom;
	}
	public void setTimefrom(String timefrom) {
		this.timefrom = timefrom;
	}
	public String getTimeto() {
		return timeto;
	}
	public void setTimeto(String timeto) {
		this.timeto = timeto;
	}
	public String getSpareparts() {
		return spareparts;
	}
	public void setSpareparts(String spareparts) {
		this.spareparts = spareparts;
	}
	public String getAttendby() {
		return attendby;
	}
	public void setAttendby(String attendby) {
		this.attendby = attendby;
	}

	@Override
	public String toString() {
		return "DailyLogDTO [id=" + id + ", loadmax=" + loadmax + ", loadmin=" + loadmin + ", voltmax=" + voltmax
				+ ", voltmin=" + voltmin + ", frequencymax=" + frequencymax + ", frequencymin=" + frequencymin
				+ ", pfmax=" + pfmax + ", pfmin=" + pfmin + ", powerdip=" + powerdip + ", remark=" + remark
				+ ", machine=" + machine + ", description=" + description + ", timefrom=" + timefrom + ", timeto="
				+ timeto + ", spareparts=" + spareparts + ", attendby=" + attendby + ", jobtype=" + jobtype + ", dates="
				+ dates + ", substation=" + substation + ", recordtype=" + recordtype + ", status=" + status
				+ ", shift=" + shift + "]";
	}
	
}

package com.zzrenfeng.zhsx.spark.domain;

import java.io.Serializable;

public class UVTask implements Serializable{

	/**
	 * 封装任务参数，通过参数来过滤，没有的参数就为就是直接通过的，就不对其进行过滤
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String role;
	private String privce;//省
	private String city;//市
	
	private String county;//县区
	private String sex;
	private String startTime;
	private String endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPrivce() {
		return privce;
	}
	public void setPrivce(String privce) {
		this.privce = privce;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

package com.employee.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performanceData")
public class PerformanceReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "empId")
	private Integer empId;
	
	@Column(name = "projectDelivery")
	private String projectDelivery;
	
	@Column(name = "teamComm")
	private String teamComm;
	
	@Column(name="description")
	private String description;
	
	@Column(name="userIds")
	private String userIds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getProjectDelivery() {
		return projectDelivery;
	}

	public void setProjectDelivery(String projectDelivery) {
		this.projectDelivery = projectDelivery;
	}

	public String getTeamComm() {
		return teamComm;
	}

	public void setTeamComm(String teamComm) {
		this.teamComm = teamComm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	
}

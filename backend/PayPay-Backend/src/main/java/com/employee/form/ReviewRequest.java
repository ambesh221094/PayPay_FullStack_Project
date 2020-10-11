package com.employee.form;

import java.util.List;

public class ReviewRequest {
	
	private Integer id;
	
	private Integer empId;
	
	private String projectDelivery;
	
	private String teamComm;
	
	private String description;
	
	private List<String> userIds;

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

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	
}

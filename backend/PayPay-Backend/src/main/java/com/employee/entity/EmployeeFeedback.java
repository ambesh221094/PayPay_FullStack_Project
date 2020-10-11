package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeeFeedback")
public class EmployeeFeedback {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "empId")
	private Integer empId;
	
	@Column(name = "feedback")
	private String feedback;
	
	@Column(name = "empFeedBackPending")
	private String empFeedBackPending;
	
	@Column(name = "feedbackDone")
	private Boolean feedbackDone;


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

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getEmpFeedBackPending() {
		return empFeedBackPending;
	}

	public void setEmpFeedBackPending(String empFeedBackPending) {
		this.empFeedBackPending = empFeedBackPending;
	}

	public Boolean getFeedbackDone() {
		return feedbackDone;
	}

	public void setFeedbackDone(Boolean feedbackDone) {
		this.feedbackDone = feedbackDone;
	}
	
}

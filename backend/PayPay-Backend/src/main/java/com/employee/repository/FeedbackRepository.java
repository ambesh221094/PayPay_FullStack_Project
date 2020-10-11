package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.EmployeeFeedback;

public interface FeedbackRepository extends JpaRepository<EmployeeFeedback, Integer>{

	@Query("SELECT f FROM EmployeeFeedback f WHERE f.empId =:empId")
	List<EmployeeFeedback> findByPending(@Param("empId") Integer empId);
	
}



package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.PerformanceReview;

public interface PerformanceRepository extends JpaRepository<PerformanceReview, Integer>{

	@Query("SELECT p FROM PerformanceReview p WHERE p.empId =:empId")
	PerformanceReview findPerformanceById(@Param("empId") Integer empId);
	
}

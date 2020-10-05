package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.entity.Employee;
import com.employee.entity.PerformanceReview;

public interface PerformanceRepository extends JpaRepository<PerformanceReview, Integer>{

}

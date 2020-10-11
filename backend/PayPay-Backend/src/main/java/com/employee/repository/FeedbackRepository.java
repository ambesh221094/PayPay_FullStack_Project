package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.EmployeeFeedback;

public interface FeedbackRepository extends JpaRepository<EmployeeFeedback, Integer>{

	@Query("SELECT f FROM EmployeeFeedback f WHERE f.empId =:empId and f.feedbackDone=FALSE")
	List<EmployeeFeedback> findByPending(@Param("empId") Integer empId);
	
	@Modifying
	@Query("update EmployeeFeedback e set e.feedback = :feedback,e.feedbackDone=TRUE where e.empFeedBackPending = :id")
	void updateFeedback(@Param("feedback") String feedback,@Param("id") String id);
	
	@Query("SELECT f FROM EmployeeFeedback f WHERE f.empFeedBackPending =:empId")
	List<EmployeeFeedback> findFeedbackDone(@Param("empId") String empId);
	
}



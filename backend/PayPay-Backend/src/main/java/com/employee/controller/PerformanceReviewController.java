package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.PerformanceReview;
import com.employee.repository.PerformanceRepository;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class PerformanceReviewController {
	
	@Autowired
	PerformanceRepository perRepository;
	
	@PostMapping("/performanceReview/add")
	public ResponseEntity<PerformanceReview> createReview(@RequestBody PerformanceReview performance) {
		try {
			PerformanceReview p = new PerformanceReview();
			p.setEmpId(performance.getEmpId());
			p.setProjectDelivery(performance.getProjectDelivery());
			p.setTeamComm(performance.getTeamComm());
			perRepository.save(p);
			return new ResponseEntity<>(p, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}

package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.entity.EmployeeFeedback;
import com.employee.entity.PerformanceReview;
import com.employee.form.ReviewRequest;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.FeedbackRepository;
import com.employee.repository.PerformanceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class PerformanceReviewController {

	@Autowired
	PerformanceRepository perRepository;

	@Autowired
	EmployeeRepository empRepository;

	@Autowired
	FeedbackRepository feedbackRepository;

	@PostMapping("/performanceReview/add")
	@Transactional
	public ResponseEntity<PerformanceReview> createReview(@RequestBody ReviewRequest r) throws JsonProcessingException {
		PerformanceReview pr = new PerformanceReview();
		pr.setEmpId(r.getEmpId());
		pr.setProjectDelivery(r.getProjectDelivery());
		pr.setTeamComm(r.getTeamComm());
		pr.setDescription(r.getDescription());

		ObjectMapper mapper = new ObjectMapper();
		String newJsonData = mapper.writeValueAsString(r.getUserIds());

		pr.setUserIds(newJsonData);

		EmployeeFeedback ef = new EmployeeFeedback();
		for (String u : r.getUserIds()) {
			ef.setEmpId(Integer.parseInt(u));
			ef.setEmpFeedBackPending(r.getEmpId().toString());
			ef.setFeedbackDone(false);
			feedbackRepository.save(ef);
		}

		try {
			perRepository.save(pr);
			empRepository.updateEmployee(pr.getEmpId());
			return new ResponseEntity<>(pr, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/performance/{id}")
	public ResponseEntity<PerformanceReview> getPerformanceById(@PathVariable("id") Integer id) {
		PerformanceReview e = perRepository.findPerformanceById(id);

		if (e != null) {
			return new ResponseEntity<>(e, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/performance/pending/{id}")
	public ResponseEntity<List<Employee>> getPendingUsers(@PathVariable("id") Integer id) {
		List<EmployeeFeedback> ef = feedbackRepository.findByPending(id);
		List<Employee> emp = new ArrayList<>();
		for (EmployeeFeedback e : ef) {
			emp.add(empRepository.getPendingUsersDetails(Integer.parseInt(e.getEmpFeedBackPending())));
		}

		if (ef != null) {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/performanceReview/feedback/add")
	@Transactional
	public void addFeedback(@RequestBody EmployeeFeedback f) {

		feedbackRepository.updateFeedback(f.getFeedback(),f.getEmpFeedBackPending());

	}
	
	@GetMapping("/performanceReview/feedback/{id}")
	public ResponseEntity<List<EmployeeFeedback>> getFeedbackDone(@PathVariable("id") String id) {
		List<EmployeeFeedback> ef = feedbackRepository.findFeedbackDone(id);

		if (ef != null) {
			return new ResponseEntity<>(ef, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}

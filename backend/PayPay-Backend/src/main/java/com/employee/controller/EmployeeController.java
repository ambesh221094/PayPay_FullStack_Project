package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.form.LoginRequest;
import com.employee.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepository;

	@PostMapping("/employees")
	public ResponseEntity<Employee> login(@RequestBody LoginRequest req) {
		try {
			Employee e = empRepository.findEmpByUsernameAndPassword(req.getEmployeeName(), req.getPassword());
			if (e.isAdmin()) {
				System.out.println("Logged In as Admin");
			} else {
				System.out.println("Logged In as Employee");
			}
			return new ResponseEntity<>(e, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/employees/add")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		try {
			Employee e = new Employee();
			e.setEmployeeName(employee.getEmployeeName());
			e.setEmployeeEmail(employee.getEmployeeEmail());
			empRepository.save(e);
			return new ResponseEntity<>(e, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String employee) {
		try {

			List<Employee> e = empRepository.findAll();

			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
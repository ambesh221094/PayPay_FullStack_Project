package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
			if (e != null) {
				if (e.isAdmin()) {
					System.out.println("Logged In as Admin");
				} else {
					System.out.println("Logged In as Employee");
				}
				return new ResponseEntity<>(e, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
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
			e.setPassword(employee.getPassword());
			e.setEmployeeEmail(employee.getEmployeeEmail());
			e.setAdmin(false);
			empRepository.save(e);
			return new ResponseEntity<>(e, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {

			List<Employee> e = empRepository.findEmployees();

			return new ResponseEntity<>(e, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Integer id) {
		try {
			empRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
		Employee employeelData = empRepository.findById(id).orElse(null);

		if (employeelData != null) {
			Employee e = new Employee();
			e.setEmployeeName(employee.getEmployeeName());
			e.setEmployeeEmail(employee.getEmployeeEmail());
			return new ResponseEntity<>(empRepository.save(e), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	  @GetMapping("/employees/{id}")
	  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
	    Employee e = empRepository.findById(id).orElse(null);

	    if (e!=null) {
	      return new ResponseEntity<>(e, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  @GetMapping("/employees/other/{id}")
	  public ResponseEntity<List<Employee>> getOtherEmployee(@PathVariable("id") Integer id) {
	    List<Employee> e = empRepository.getOtherEmployee(id);

	    if (e!=null) {
	      return new ResponseEntity<>(e, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

}

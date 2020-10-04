package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.employeeName = :username AND e.password= :password")
	Employee findEmpByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("SELECT e FROM Employee e WHERE e.isAdmin=FALSE")
	List<Employee> findEmployees();

}

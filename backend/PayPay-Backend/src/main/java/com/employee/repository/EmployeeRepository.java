package com.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("SELECT e FROM Employee e WHERE e.employeeName = :username AND e.password= :password")
	Employee findEmpByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("SELECT e FROM Employee e WHERE e.isAdmin=FALSE")
	List<Employee> findEmployees();
	
	@Modifying
	@Query("update Employee e set e.isReviewDone=TRUE where e.id = :id")
	void updateEmployee(@Param("id") Integer id);
	
	@Query("SELECT e FROM Employee e WHERE e.isAdmin=FALSE AND e.id!= :id")
	List<Employee> getOtherEmployee(@Param("id") Integer id);
	
	@Query("SELECT e FROM Employee e WHERE e.isAdmin=FALSE AND e.id= :id")
	Employee getPendingUsersDetails(@Param("id") Integer id);
}

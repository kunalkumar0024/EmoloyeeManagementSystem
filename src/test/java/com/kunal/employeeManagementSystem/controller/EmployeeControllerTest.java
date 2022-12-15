package com.kunal.employeeManagementSystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.entity.EmployeeEntity;
import com.kunal.employeeManagementSystem.repository.DeptRepository;
import com.kunal.employeeManagementSystem.repository.EmployeeRepository;
import com.kunal.employeeManagementSystem.serviceImpl.EmployeeEntityServcieImpl;

@SpringBootTest
public class EmployeeControllerTest {
	
	@InjectMocks
	EmployeeController empController;
	
	@Mock
	EmployeeRepository empRepo;
	
	@Mock
	DeptRepository deptRepo;
	
	@Mock
	EmployeeEntityServcieImpl empService;
	
	@Test
	@Order(1)
	public void test_getAllEmployee() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
        when(empService.getAllEmployees()).thenReturn(emp);
        assertEquals(HttpStatus.OK, empController.getAllEmployee().getStatusCode());
	}
	
	@Test
	@Order(2)
	public void test_getEmployeeById() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		EmployeeEntity emp = new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept);
		Long empId = (long) 101;
		when(empService.getEmployeeById(empId)).thenReturn(emp);
		assertEquals(HttpStatus.OK, empController.getEmployee(empId).getStatusCode());
	}
	
	@Test
	@Order(3)
	public void test_getEmployeeHaving2ndHighestSalary() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
    	when(empService.getEmployeeHaving2ndHighestSalaryy()).thenReturn(emp.get(1));
    	assertEquals(HttpStatus.OK, empController.getEmployeeHaving2ndHighestSalary().getStatusCode());
	}
	
	@Test
	@Order(4)
	public void test_getEmployeeByDepartmentId() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
		Long deptId = (long) 1;
		assertEquals(HttpStatus.OK, empController.getEmployeeByDepartmentId(deptId).getStatusCode());
	}
	
	@Test
	@Order(5)
	public void test_saveEmployeeDetail() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		Long deptId = (long) 1;
		DeptEntity get = deptRepo.getById(deptId);
		if(get != null) {
		EmployeeEntity emp = new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept);
		when(empService.saveEmployeeDetail(emp)).thenReturn(emp);
		assertEquals(HttpStatus.OK, empController.saveEmployeeDetails(emp, deptId).getStatusCode());
		}
	}
}

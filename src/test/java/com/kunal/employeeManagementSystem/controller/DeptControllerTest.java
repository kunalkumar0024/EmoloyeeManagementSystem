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
import org.springframework.http.ResponseEntity;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.repository.DeptRepository;
import com.kunal.employeeManagementSystem.serviceImpl.DeptEntityServiceImpl;

@SpringBootTest
public class DeptControllerTest {
	
	@Mock
	DeptEntityServiceImpl departmentService;
	
	@Mock
	DeptRepository deptRepo;
	
	@InjectMocks
	public DeptController departmentControler;
	
	@Test
	@Order(1)
	public void test_add_Department() {
		DeptEntity myDept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		when(departmentService.saveDepartments(myDept)).thenReturn(myDept);
		ResponseEntity<Object> res = departmentControler.saveDepartment(myDept);
		assertEquals(HttpStatus.OK,res.getStatusCode());
	}
	
	@Test
	@Order(2)
	public void test_getDepartmentsById() {
		DeptEntity myDept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		Long deptId = (long) 1;
		when(departmentService.getDepartmentsById(deptId)).thenReturn(myDept);
		ResponseEntity<Object> res = departmentControler.getDepartmentById(deptId);
		assertEquals(HttpStatus.OK,res.getStatusCode());
	}
	
	@Test
	@Order(4)
	public void test_getAllDepartment() {
		List<DeptEntity> myDepartment = new ArrayList<>();
		myDepartment.add(new DeptEntity((long) 1,"AEPS","BBSR",12));
		myDepartment.add(new DeptEntity((long) 2,"mATM","BBSR",8));
		when(departmentService.getAllDepartments()).thenReturn(myDepartment);
		assertEquals(HttpStatus.OK, departmentControler.getallDepartment().getStatusCode());		
	}	

}

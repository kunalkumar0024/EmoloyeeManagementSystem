package com.kunal.employeeManagementSystem.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.repository.DeptRepository;

@SpringBootTest
public class DeptEntityServiceImplTest {
	
	@Mock
	DeptRepository deptRepo;
	
	@InjectMocks
	public DeptEntityServiceImpl  deptService;
	
	@Test
	@Order(1)
	public void test_saveDepartments() {
		DeptEntity myDept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		Long deptId = (long) 1;
		when(deptRepo.getById(deptId)).thenReturn(myDept);
		assertEquals(myDept, deptService.getDepartmentsById(deptId));
		assertNotNull(myDept);
	}
	
	@Test
	@Order(2)
	public void test_getAllDepartments() {
		List<DeptEntity> myDepartment = new ArrayList<>();
		myDepartment.add(new DeptEntity((long) 1,"AEPS","BBSR",12));
		myDepartment.add(new DeptEntity((long) 2,"mATM","BBSR",8));
		when(deptRepo.findAll()).thenReturn(myDepartment);
		assertEquals(2, deptService.getAllDepartments().size());
		assertNotNull(myDepartment);
	}
	
	@Test
	@Order(3)
	public void test_getDepartmentById() {
		DeptEntity myDept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		Long deptId = (long) 1;
		when(deptRepo.getById(deptId)).thenReturn(myDept);
		assertEquals(myDept, deptService.getDepartmentsById(deptId));
		System.out.println(deptService.getDepartmentsById(deptId));
	}
	
//	@Test
//	@Order(4)
//	public void test_getDepartmentByMaxStrength() {
//		List<Department> myDepartment = new ArrayList<>();
//		myDepartment.add(new Department((long) 2,"mATM",8));
//		myDepartment.add(new Department((long) 1,"AEPS",12));
//		when(deptRepo.findAll()).thenReturn(myDepartment);
//		when(deptRepo.findByMaximumStrenght()).thenReturn(myDepartment.get(1));
//		assertEquals(12,deptService.getDeptByHighestStrength().getDeptStrength());
//	}
	
//	@Test
//	public void getDepartmentByIdTest() {
//		DeptEntity dept = new DeptEntity();
//		
//		dept.setDeptId((long) 101);
//		dept.setDeptName("AEPS");
//		dept.setDeptAddress("BBSR");
//		dept.setTotalEmployees(5);
//		
//		deptService.getDepartmentsById((long) 101);
//		System.out.println(deptService.getDepartmentsById((long) 101));
//		assertEquals(dept, deptService.getDepartmentsById((long) 101));
//		
//	}
//	
//	@Test
//	public void getAllDepartmentsTest() {
//		DeptEntity d1 = new DeptEntity();
//		List<DeptEntity> dept = new ArrayList<>();
//		
//		d1.setDeptId((long) 101);
//		d1.setDeptName("AEPS");
//		d1.setDeptAddress("BBSR");
//		d1.setTotalEmployees(5);
//		
//		deptService.getAllDepartments();
//	}
}

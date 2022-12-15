package com.kunal.employeeManagementSystem.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.entity.EmployeeEntity;
import com.kunal.employeeManagementSystem.repository.DeptRepository;
import com.kunal.employeeManagementSystem.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeEntityServiceImplTest {
	
	@Mock
	private EmployeeRepository empRepo;
	
	@Mock
	private DeptRepository deptRepo;
	
	@InjectMocks
	private EmployeeEntityServcieImpl empService;

	@Test
	@Order(1)
	public void test_getEmployeeById() {
		DeptEntity myDept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		EmployeeEntity emp = new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,myDept);
		Long empId = (long) 101;
		when(empRepo.getById(empId)).thenReturn(emp);
		assertEquals(emp, empService.getEmployeeById(empId));
	}
	
	@Test
	@Order(2)
	public void test_getAllEmployees() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
        empRepo.saveAll(emp);
        when(empRepo.findAll()).thenReturn(emp);
        assertEquals(2, empService.getAllEmployees().size());
	}
	
	@Test
	@Order(3)
	public void test_getEmployeeHaving2ndHighestSalary() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
        empRepo.saveAll(emp);
        when(empRepo.findEmployeeeHaving2ndHighestSalary()).thenReturn(emp.get(1));
        assertEquals(emp.get(1), empService.getEmployeeHaving2ndHighestSalaryy());   
	}
	
	@Test
	@Order(4)
	public void test_getEmployeeByDepartmentId() {
		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",12);
		List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
        Long deptId = (long) 1;
        when(empService.getEmployeesByDepartmentId(deptId)).thenReturn(emp);
        assertEquals(2, empService.getEmployeesByDepartmentId(deptId).size());
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
		assertEquals(emp, empService.saveEmployeeDetail(emp));
		}
	}
}

package com.kunal.employeeManagementSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.entity.EmployeeEntity;

@SpringBootTest
public class EmployeeRepositoryTest {

	@Mock
    private EmployeeRepository empRepo;
	
	@Mock
    private DeptRepository deptRepo;
 
    @Test
    @Order(1)
    public void test_findAllByDeptId() {
    	DeptEntity dept = new DeptEntity((long) 1, "AEPS","BBSR", 12);
        deptRepo.save(dept);
        List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
        empRepo.saveAll(emp);
        Long deptId = (long) 1;
        List<EmployeeEntity> actualResult = empRepo.findALLByDeptId(deptId);
        assertNotNull(actualResult);
    }
    
    @Test
    @Order(2)
    public void test_findEmployeeHaving2ndHighestSalary() {
     	DeptEntity dept = new DeptEntity((long) 1, "AEPS","BBSR", 12);
    	List<EmployeeEntity> emp = new ArrayList<>();
    	emp.add(new EmployeeEntity(101,"Kunal","BBSR",19000,0.7,1,dept));
    	emp.add(new EmployeeEntity(102,"Prince","BBSR",17000,0.7,1,dept));
    	when(empRepo.findEmployeeeHaving2ndHighestSalary()).thenReturn(emp.get(1));
    	EmployeeEntity actual = empRepo.findEmployeeeHaving2ndHighestSalary();
    	assertEquals(emp.get(1), actual);
   }
}

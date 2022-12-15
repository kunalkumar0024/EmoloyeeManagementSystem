package com.kunal.employeeManagementSystem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.kunal.employeeManagementSystem.entity.DeptEntity;

@SpringBootTest
public class DeptRepositoryTest {


	@Mock
    private DeptRepository deptRepo;
 
    @Test
    @Order(1)
    public void test_findByDeptId() {
        DeptEntity dept = new DeptEntity((long) 1, "AEPS","BBSR", 12);
        deptRepo.save(dept);
        Long deptId = (long) 1;
        when(deptRepo.getById(deptId)).thenReturn(dept);
        DeptEntity actualResult = deptRepo.getById((long) 1);
        assertNotNull(actualResult);
        assertEquals(dept,actualResult);
    }
    
    
}

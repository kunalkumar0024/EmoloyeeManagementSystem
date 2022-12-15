package com.kunal.employeeManagementSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunal.employeeManagementSystem.entity.EmployeeEntity;
import com.kunal.employeeManagementSystem.repository.EmployeeRepository;

@Service
public class EmployeeEntityServcieImpl {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeEntity getEmployeeById(Long empId) {
		return employeeRepository.getById(empId);
	}
	
	public List<EmployeeEntity> getAllEmployees(){
		return employeeRepository.findAll();
	}

	public EmployeeEntity saveEmployeeDetail(EmployeeEntity employeeEntity) {
		return employeeRepository.save(employeeEntity);
	}
	
	public EmployeeEntity getEmployeeHaving2ndHighestSalaryy() {
		return employeeRepository.findEmployeeeHaving2ndHighestSalary();
	}
	
	public List<EmployeeEntity> getEmployeesByDepartmentId(Long deptId) {
		return employeeRepository.findALLByDeptId(deptId);
	}
}

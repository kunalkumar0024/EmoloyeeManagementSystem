package com.kunal.employeeManagementSystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.entity.EmployeeEntity;
import com.kunal.employeeManagementSystem.entity.Status;
import com.kunal.employeeManagementSystem.repository.DeptRepository;
import com.kunal.employeeManagementSystem.serviceImpl.DeptEntityServiceImpl;
import com.kunal.employeeManagementSystem.serviceImpl.EmployeeEntityServcieImpl;


@RestController
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeEntityServcieImpl employeeServcie;
	
	@Autowired
	private DeptEntityServiceImpl deptService;
	
	@Autowired
	private DeptRepository deptRepo;
	
	EmployeeEntity emp = new EmployeeEntity();
	
	Status status = new Status();
	
	
	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Object> getEmployee(@PathVariable Long empId) {
		try {
		employeeServcie.getEmployeeById(empId);
		logger.info("Employee successfully fetched with this id "+ employeeServcie.getEmployeeById(empId));
		status.setStatusCode("0");
		status.setStatusDesc("Employee Data is present in Employee table");
		return new ResponseEntity<>(status,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Employee not found with this id "+ employeeServcie.getEmployeeById(empId));
			status.setStatusCode("-1");
			status.setStatusDesc("Internal Server Error");
			return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllEmployees") 
	public ResponseEntity<List<EmployeeEntity>> getAllEmployee(){
		try {
		employeeServcie.getAllEmployees();
		logger.info("Employees data fetched successfully "+ employeeServcie.getAllEmployees());
//		status.setStatusCode("0");
//		status.setStatusDesc("Employee Data is present in Employee table");
		return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.OK).ok(employeeServcie.getAllEmployees());
		}catch(Exception e) {
			logger.error("Employees data can't be fetched ");
//			status.setStatusCode("-1");
//			status.setStatusDesc("Some employees data are missing");
			return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.NOT_FOUND).ok(employeeServcie.getAllEmployees());
		}
	}

	@PostMapping("/saveEmployeeDetail/{deptId}")
	public ResponseEntity<Object> saveEmployeeDetails(@RequestBody EmployeeEntity employeeEntity, @PathVariable Long deptId) {
		DeptEntity dept = deptRepo.getById(deptId);
		int counter = deptService.getDepartmentsById(deptId).getTotalEmployees();
		try {
			if(dept != null) {
				++counter;
				employeeServcie.saveEmployeeDetail(employeeEntity);
				logger.info("Employee data inserted successfully "+employeeServcie.saveEmployeeDetail(employeeEntity));
				dept.setTotalEmployees(counter);
				deptRepo.save(dept);
				status.setStatusCode("0");
				status.setStatusDesc("Employee Data inserted Succesfully");
				return new ResponseEntity<>(status,HttpStatus.OK);
			}
			}catch(Exception e) {
				logger.error("Internal Server Error ! ");
				status.setStatusCode("-1");
				status.setStatusDesc("Internal Server Error");
				return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		logger.warn("Department not found in department table ! ");	
		status.setStatusCode("-1");
		status.setStatusDesc("Employee Data can't be inserted as department not found");
		return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getEmployeeHaving2ndHighestSalary")
	public ResponseEntity<Object> getEmployeeHaving2ndHighestSalary() {
		try {
		employeeServcie.getEmployeeHaving2ndHighestSalaryy();
		logger.info("Employee data fetched successfully having 2nd highest salary "+employeeServcie.getEmployeeHaving2ndHighestSalaryy());
		status.setStatusCode("0");
		status.setStatusDesc("Employee found by 2nd highest salary");
		return new ResponseEntity<>(status,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Employee not found with 2nd highest salary");
			status.setStatusCode("-1");
			status.setStatusDesc("Employee not found with 2nd highest salary");
			return new ResponseEntity<>(status,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getEmployeeByDepartmentId/{deptId}")
	public ResponseEntity<List<EmployeeEntity>> getEmployeeByDepartmentId(@PathVariable Long deptId){
		
		try {
		employeeServcie.getEmployeesByDepartmentId(deptId);
		logger.info("Employee fetched by department ID successfully "+ employeeServcie.getEmployeesByDepartmentId(deptId));	
//		status.setStatusCode("0");
//		status.setStatusDesc("Employee found with department Id");
		return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.OK).ok(employeeServcie.getEmployeesByDepartmentId(deptId));
		}catch(Exception e) {
			logger.error("Employee data not found");
//			status.setStatusCode("-1");
//			status.setStatusDesc("Employee not found with this department id");	
			return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.NOT_FOUND).ok(employeeServcie.getEmployeesByDepartmentId(deptId));
		}
	}
}

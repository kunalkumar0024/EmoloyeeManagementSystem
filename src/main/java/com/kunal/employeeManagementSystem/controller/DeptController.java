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
import com.kunal.employeeManagementSystem.entity.Status;
import com.kunal.employeeManagementSystem.serviceImpl.DeptEntityServiceImpl;


@RestController
public class DeptController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	Status status = new Status();
	
	@Autowired
	private DeptEntityServiceImpl deptService;
	
	@GetMapping("/getAllDepartment")
	public ResponseEntity<List<DeptEntity>> getallDepartment(){
		try {
		deptService.getAllDepartments();
		logger.info("Departments successfully fetched "+ deptService.getAllDepartments());
//		status.setStatusCode("0");
//		status.setStatusDesc("All department data found");
		return new ResponseEntity<List<DeptEntity>>(HttpStatus.OK).ok(deptService.getAllDepartments());
		}catch(Exception e) {
//			status.setStatusCode("-1");
//			status.setStatusDesc("Departments data are missing");
			logger.error("Departments data are missing ");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND).ok(deptService.getAllDepartments());
		}
	}

	@PostMapping("/saveDepartmentDetail")
	public ResponseEntity<Object> saveDepartment(@RequestBody DeptEntity deptEntity) {
		try {
		deptService.saveDepartments(deptEntity);
		logger.info("Department inserted successfully "+deptService.saveDepartments(deptEntity));
		status.setStatusCode("0");
		status.setStatusDesc("Department added Successfully");
		return new ResponseEntity<Object>(status,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Department can't be added ");
			status.setStatusCode("-1");
			status.setStatusDesc("Department can't be added");
			return new ResponseEntity<Object>(status,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getDepartmentById/{deptId}")
	public ResponseEntity<Object> getDepartmentById(@PathVariable Long deptId) {
		try {
		deptService.getDepartmentsById(deptId);
		logger.info("Departments successfully fetched by ID "+ deptService.getDepartmentsById(deptId));
		status.setStatusCode("0");
		status.setStatusDesc("Department found with this id");
		return new ResponseEntity<Object>(status,HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Department not found with this id");
			status.setStatusCode("0");
			status.setStatusDesc("Department not found with this id");
			return new ResponseEntity<Object>(status,HttpStatus.NOT_FOUND);
		}
	}

	
}

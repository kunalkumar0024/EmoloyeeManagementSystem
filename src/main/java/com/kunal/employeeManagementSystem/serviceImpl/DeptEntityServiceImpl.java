package com.kunal.employeeManagementSystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
import com.kunal.employeeManagementSystem.repository.DeptRepository;

@Service
public class DeptEntityServiceImpl {
	
	@Autowired
	private DeptRepository deptRepository;
	
	public List<DeptEntity> getAllDepartments() {
		return deptRepository.findAll();
	}
	
	public DeptEntity saveDepartments(DeptEntity deptEntity) {
		return deptRepository.save(deptEntity);
	}
	
	public DeptEntity getDepartmentsById(Long deptId) {
		return deptRepository.getById(deptId);
	}
	
}

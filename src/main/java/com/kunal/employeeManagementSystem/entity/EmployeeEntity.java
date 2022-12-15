package com.kunal.employeeManagementSystem.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
	
	@Id
	@Column(name = "empId")
	private Long empId;
	@Column(name = "empName", nullable = false)
	private String empName;
	@Column(name = "empAddress", nullable = false)
	private String empAddress;
	@Column(name = "empSalary", nullable = false)
	private BigDecimal empSalary;
	@Column(name = "totalExperience", nullable = false)
	private Double totalExperience;
	@Column(name = "deptId",nullable = false, insertable = false, updatable = false)
	private Long deptId;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deptId", referencedColumnName = "deptId")
	private DeptEntity dept;
	
	public EmployeeEntity(int i, String empName2, String empAddress2, int j, double totalExperience2, int k,
			DeptEntity dept2) {
		// TODO Auto-generated constructor stub
	}
}

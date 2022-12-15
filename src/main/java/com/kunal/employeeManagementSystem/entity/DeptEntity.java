package com.kunal.employeeManagementSystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptEntity implements Serializable{//
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "deptId")
	private Long deptId;
	@Column(name = "deptName")
	private String deptName;
	@Column(name = "deptAddress")
	private String deptAddress;
	@Column(name = "totalEmployees")
	private int totalEmployees;
}

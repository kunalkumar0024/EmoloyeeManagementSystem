package com.kunal.employeeManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kunal.employeeManagementSystem.entity.DeptEntity;
@Repository
public interface DeptRepository extends JpaRepository<DeptEntity, Long>{

	void save(Optional<DeptEntity> dept);

}

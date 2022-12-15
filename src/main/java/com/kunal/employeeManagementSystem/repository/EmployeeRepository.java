package com.kunal.employeeManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kunal.employeeManagementSystem.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{

	@Query(value = "select * from employee group by empSalary order by  empSalary desc limit 1,1",nativeQuery = true)
	EmployeeEntity findEmployeeeHaving2ndHighestSalary();
		
	@Query(value="select * from employee where deptId =:deptId", nativeQuery=true)
	List<EmployeeEntity> findALLByDeptId(@Param("deptId") Long deptId);

}

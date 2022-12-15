package com.kunal.employeeManagementSystem.integrationTest;


import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.kunal.employeeManagementSystem.repository.DeptRepository;


@SpringBootTest
public class EmployeeControllerIntegrationTest {
	
	@Autowired
	DeptRepository dept;

	@Order(2)
	@Test
	void getEmployeeByIdTest() throws JSONException {
		 String expected = "{\r\n"
		 		+ "    \"statusCode\": \"0\",\r\n"
		 		+ "    \"statusDesc\": \"Employee Data is present in Employee table\"\r\n"
		 		+ "}";
		 TestRestTemplate testRestTemplate = new TestRestTemplate();
		 ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getEmployee/101", String.class);
		 JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Order(3)
	@Test
	void getEmployeeByDeptIdTest() throws JSONException {
		 String expected = "[\r\n"
		 		+ "    {\r\n"
		 		+ "        \"empId\": 101,\r\n"
		 		+ "        \"empName\": \"Kunal Kumar\",\r\n"
		 		+ "        \"empAddress\": \"BBSR\",\r\n"
		 		+ "        \"empSalary\": 19000.00,\r\n"
		 		+ "        \"totalExperience\": 1.1,\r\n"
		 		+ "        \"deptId\": 1,\r\n"
		 		+ "        \"dept\": {\r\n"
		 		+ "            \"deptId\": 1,\r\n"
		 		+ "            \"deptName\": \"AEPS\",\r\n"
		 		+ "            \"deptAddress\": \"BBSR\",\r\n"
		 		+ "            \"totalEmployees\": 8\r\n"
		 		+ "        }\r\n"
		 		+ "    }\r\n"
		 		+ "]";
		 TestRestTemplate testRestTemplate = new TestRestTemplate();
		 ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getEmployeeByDepartmentId/1", String.class);
		 JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Order(4)
	@Test
	void getEmployeeBy2ndHighestSalaryTest() throws JSONException {
		 String expected = "{\r\n"
		 		+ "    \"statusCode\": \"0\",\r\n"
		 		+ "    \"statusDesc\": \"Employee found by 2nd highest salary\"\r\n"
		 		+ "}";
		 TestRestTemplate testRestTemplate = new TestRestTemplate();
		 ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getEmployeeHaving2ndHighestSalary", String.class);
		 JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Order(5)
	@Test
	void getAllEmployeeSTest() throws JSONException {
		 String expected = "[\r\n"
		 		+ "    {\r\n"
		 		+ "        \"empId\": 101,\r\n"
		 		+ "        \"empName\": \"Kunal Kumar\",\r\n"
		 		+ "        \"empAddress\": \"BBSR\",\r\n"
		 		+ "        \"empSalary\": 19000.00,\r\n"
		 		+ "        \"totalExperience\": 1.1,\r\n"
		 		+ "        \"deptId\": 1,\r\n"
		 		+ "        \"dept\": {\r\n"
		 		+ "            \"deptId\": 1,\r\n"
		 		+ "            \"deptName\": \"AEPS\",\r\n"
		 		+ "            \"deptAddress\": \"BBSR\",\r\n"
		 		+ "            \"totalEmployees\": 8\r\n"
		 		+ "        }\r\n"
		 		+ "    }\r\n"
		 		+ "]";
		 TestRestTemplate testRestTemplate = new TestRestTemplate();
		 ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getAllEmployees", String.class);
		 JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
//	@Order(1)
//	@Test
//	void addEmpTest() throws JSONException {
//		DeptEntity dept = new DeptEntity((long) 1,"AEPS","BBSR",7);
//		Long deptId = (long) 1;
//		EmployeeEntity emp = new EmployeeEntity(101,"Kunal Kumar","BBSR",19000,1.1,1,dept);
//		String expected = "{\r\n"
//				+ "    \"statusCode\": \"0\",\r\n"
//				+ "    \"statusDesc\": \"Employee Data inserted Succesfully\"\r\n"
//				+ "}"; 
//		TestRestTemplate testRestTemplate = new TestRestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<EmployeeEntity> request = new HttpEntity<EmployeeEntity>(emp,headers);
//		ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/saveEmployeeDetail/1",request, String.class);
//		assertEquals(HttpStatus.OK, response.getStatusCode());
//		JSONAssert.assertEquals(expected, response.getBody(), false);
//	}
}

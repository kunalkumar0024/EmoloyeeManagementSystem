package com.kunal.employeeManagementSystem.integrationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.kunal.employeeManagementSystem.entity.DeptEntity;


@SpringBootTest
public class DeptControllerIntegrationTest {

	@Order(2)
	@Test
	void getDepartmentByIdTest() throws JSONException {
		 String expected = "{\r\n"
		 		+ "    \"statusCode\": \"0\",\r\n"
		 		+ "    \"statusDesc\": \"Department found with this id\"\r\n"
		 		+ "}";
		 TestRestTemplate testRestTemplate = new TestRestTemplate();
		 ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getDepartmentById/1", String.class);
		 System.out.println(response.getStatusCode());
		 System.out.println(response.getBody());
		 JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	
	@Order(1)
	@Test
	void saveDepartmentTest() throws JSONException {
		DeptEntity dept = new DeptEntity((long) 3,"BBPS","BBSR",5);
		String expected = "{\r\n"
				+ "    \"statusCode\": \"0\",\r\n"
				+ "    \"statusDesc\": \"Department added Successfully\"\r\n"
				+ "}";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DeptEntity> request = new HttpEntity<DeptEntity>(dept,headers);
		ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8080/saveDepartmentDetail",request, String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(3)
	void getAllDepartmentTest() throws JSONException {
		
		String expected = "[\r\n"
				+ "    {\r\n"
				+ "        \"deptId\": 1,\r\n"
				+ "        \"deptName\": \"AEPS\",\r\n"
				+ "        \"deptAddress\": \"BBSR\",\r\n"
				+ "        \"totalEmployees\": 8\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"deptId\": 3,\r\n"
				+ "        \"deptName\": \"BBPS\",\r\n"
				+ "        \"deptAddress\": \"BBSR\",\r\n"
				+ "        \"totalEmployees\": 5\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/getAllDepartment", String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);	
	}	
}

package com.rest;

import java.util.ArrayList;
import java.util.List;

import jdk.Exported;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rest.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestfulTestApplicationTests extends Assert{

	RestTemplate restTemplate = new RestTemplate();
	private static final String BASE_URI = "http://localhost:8080/customers";
	
	@Test
	public void test_get_all_customers_getForObject(){
		RestTemplate template = new RestTemplate();
		List<Customer> list = template.getForObject(BASE_URI, List.class);
		System.out.println("------------------" + list);
		assertNotNull(list);
	}
	
	//try to understand difference between getForObject and getForEntity
	@Test
	public void test_get_all_customers_getForEntity(){
		RestTemplate template = new RestTemplate();
		ResponseEntity<List> response = template.getForEntity(BASE_URI, List.class);
		assertEquals(response.getStatusCode().value(), 200);
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void test_delete_operation_failed_exception(){
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/customers/1", HttpMethod.DELETE,null,String.class );
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test(expected = HttpClientErrorException.class)
	public void test_delete_operation_success(){
		restTemplate.delete("http://localhost:8080/customers/1");
		ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8080/customers/2", Customer.class);
		//this will throw exception as customer is not found
	}
	
}


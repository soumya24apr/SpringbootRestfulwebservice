package com.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rest.exception.CustomerNotFoundException;
import com.rest.model.Customer;


@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	public static List<Customer> customerList = new ArrayList<Customer>();

	static{
		customerList.add(new Customer("1","Soumya"));
		customerList.add(new Customer("2","Jonathan"));
	}
	
	//get all details
	@RequestMapping(value="" , method= RequestMethod.GET)
	@ResponseBody
	public List<Customer> getAllCustomers(){
		return customerList;
	}
	
	//add
	@RequestMapping(value="/create",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String addNewCustomer(@RequestBody Customer customer){
		System.out.println("in add new customer" + customer.getId());
		customerList.add(customer);
		return "New Customer added successfully";
	}
	
	//delete
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteCustomer(@PathVariable("id") String id){
		Customer customer_Obj = null;
		for(Customer c:customerList){
			if(c.getId().equals(id)){
				customer_Obj = c;
			}
		}
		if(customer_Obj == null){
			//throw customer not found exception
			throw new CustomerNotFoundException();
		}else{
			customerList.remove(customer_Obj);
		}
		return "Customer " + id + " deleted successfully";
	}
}


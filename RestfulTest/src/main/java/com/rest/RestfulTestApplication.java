package com.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@RequestMapping("/")
public class RestfulTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulTestApplication.class, args);
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String greetings(@RequestBody String name){
		return "Hello" + name;
	}
	
	/*@RequestMapping("")
	@ResponseBody
	public String hello(){
		return "Hello World";
	}
*/
}


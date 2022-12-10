package com.shopopedia.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/servertest")
	public ResponseEntity<String> testApi(){
		//C-S-R
		String  msg = "ServerUp!";
		 return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
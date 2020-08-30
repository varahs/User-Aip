package com.example.databaseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.databaseproject.service.UserImpl;



@RestController

public class UserController {

	@Autowired
	private UserImpl userService;
	
//	@PostMapping("user/register")
//	public ResponseEntity<Response> userRegister(@RequestBody UserDto user, HttpServletRequest request)  {
//		Response response=userService.register(user,request);
//		
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//	}
//	
//	@PutMapping(value="user/login")
//	public ResponseEntity<Response> userLogin(@RequestBody LoginDto loginDto, HttpServletResponse httpResponse) {
//		
//		Response response=userService.login(loginDto,httpResponse);
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
}

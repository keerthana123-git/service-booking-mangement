package com.cts.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.usermanagement.exception.InvalidTokenException;
import com.cts.usermanagement.feign.AuthClient;
import com.cts.usermanagement.model.AppUser;
import com.cts.usermanagement.service.UserService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthClient authClient;
	
	@GetMapping("/awsBooking")
	public String welcome() {
    	return "UserManagement Service Deployed to Cloud";
	}
	
    @PostMapping("/user")
	public AppUser registerUser(@RequestBody AppUser appUser) {
    	return userService.registerUser(appUser);
	}
 
    
	@GetMapping("/user")
	public List<AppUser> getUsers(@RequestHeader(name="authorization",required = true)String token)throws InvalidTokenException{
		if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		return userService.getUsers();		
	}
	
	@DeleteMapping("/deleteUsers")
	public void deleteUser(@RequestParam Integer id,@RequestHeader(name="authorization",required = true)String token)throws InvalidTokenException {
		if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		userService.deleteUser(id);
	}
   @PostMapping("/user/login")
  	public AppUser loginUser(@RequestBody AppUser appUser) {
      	
      	return userService.loginUser(appUser);
  	}
	@PutMapping("/user")
	public AppUser updateStudent(@RequestHeader(name="authorization",required = true)String token,@RequestBody AppUser appUser)throws InvalidTokenException {
		if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		return  userService.updateUser(appUser);
	}
	
	@GetMapping("/user/{id}")
	public AppUser getUserById(@PathVariable("id") Integer id,@RequestHeader(name="authorization",required = true)String token)throws InvalidTokenException {
		if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		return userService.getUserById(id);
	}
	

}

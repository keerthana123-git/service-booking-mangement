package com.cts.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cts.usermanagement.dao.UserDao;
import com.cts.usermanagement.exception.UserNotFoundException;
import com.cts.usermanagement.model.AppUser;
import com.cts.usermanagement.model.MyUser;


@Service
public class UserService  {

	@Autowired
	private UserDao userDao;
	
	
	
	public AppUser registerUser(AppUser appUser) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String userid = appUser.getmailId();
		String username = appUser.getName();
		String password = appUser.getPassword();
		MyUser data = new MyUser(userid,password,username);
		HttpEntity<?> entity = new HttpEntity<Object>(data,headers);
		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8084/api/auth/adduser", HttpMethod.POST, entity, Object.class);
		return userDao.save(appUser);
	}
	public AppUser loginUser(AppUser appUser) {
		
		return null;
	}
	
	public List<AppUser> getUsers(){
		return (List<AppUser>) userDao.findAll();		 
	}
	
	public void deleteUser(Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AppUser appuser = userDao.findById(id).orElse(null);
		String id1 = appuser.getmailId();
		MyUser data = new MyUser(id1,null,null);
		HttpEntity<?> entity = new HttpEntity<Object>(data,headers);
		ResponseEntity<Object> responseEntity = restTemplate.exchange("http://localhost:8084/api/auth/deleteuser", HttpMethod.POST, entity, Object.class);
		userDao.deleteById(id);
	}
	
	public AppUser updateUser(AppUser appUser) throws UserNotFoundException {
		
		
		Integer id = appUser.getId();
		AppUser us = userDao.findById(id).orElse(appUser);
		us.setmailId(appUser.getmailId());
		us.setMobile(appUser.getMobile());
		us.setName(appUser.getName());
		us.setPassword(appUser.getPassword());
		us.setRedgDate(appUser.getRedgDate());
		
		return userDao.save(us);
	}
	
	public AppUser getUserById(Integer id) {
		return userDao.findById(id).orElse(null);
	}

	
	

	
}

package com.cts.usermanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.usermanagement.dao.UserDao;
import com.cts.usermanagement.model.AppUser;
import com.cts.usermanagement.service.UserService;



@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserManagementApplicationTests {
    
	@Order(1)
	private UserTest userTest;
	@Test
    void testgetId(){
		int expectedResult=1;
	
		int actualResult=1;
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	@Test
	@Order(2)
	public void testRegisterUser() throws ParseException {
		AppUser appUser = new AppUser();
		appUser.setName("Test Name");
		appUser.setmailId("Test@gmail.com");
		appUser.setMobile(9835000000L);
		appUser.setPassword("test");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-09");
		java.sql.Date date1 = new java.sql.Date(date.getTime());
		appUser.setRedgDate(date1);
		userDao.save(appUser);
		assertNotNull(userDao.findById(4).get());
	}
	
	@Test
	@Order(3)
	public void testGetUsers() {
		List<AppUser> users = new ArrayList<>();
		users = userService.getUsers();
		System.out.println(users);
		assertThat(users).size().isGreaterThan(0);		
	}
	
	@Test
	@Order(4)
	public void testGetSingleUser() {
		AppUser appUser = userDao.findById(4).get();
		assertEquals("Test Name", appUser.getName());
	}
	
	@Test
	@Order(5)
	public void testDeleteUser() {
		userDao.deleteById(4);
		assertThat(userDao.existsById(4)).isFalse();
	}

}

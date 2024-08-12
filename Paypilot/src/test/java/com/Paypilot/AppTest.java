package com.Paypilot;


import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import controller.UserController;
import model.User;
import repo.UserRepository;
import service.UserService;

import java.util.Map;


public class AppTest {
    Main main;
    UserController userController;
    User user;
    UserRepository userRepository;
    UserService userService;
    
    @Before
    public void setUp() {
    	main = new Main();
    	userController = new UserController();
    	user = new User();
    	userRepository = new UserRepository();
    	userService = new UserService();
    }
    
    @Test
    public void testValidEmailService() {
    	boolean expected = true;
    	boolean actual = userService.isValidEmail("apple@apple.com"); //Positive Test
    	assertEquals(expected, actual);
    	expected  = false;
    	actual = userService.isValidEmail("apple.com"); //Negative Test
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testValidPasswordTest() {
    	boolean expected = true;
    	boolean actual = userService.isValidPassword("Squad@123"); //Positive Test
    	assertEquals(expected, actual);
    	expected  = false;
    	actual = userService.isValidEmail("Squad123"); //Negative Test
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testRegisterUserSuccess() {
    	User user = new User("user001", "john.doe@example.com", "password123", "ABCDE1234F", "1234567890", "HDFC0000123", "HDFC");
    	String expected = "Account already exists";
    	String actual = userService.registerUser(user); 
    	assertEquals(expected, actual);
    	user = new User("user006", "user005@example.com", "Password@123", "ABCDE123F", "1234567890", "HDFC0000123", "HDFC");
    	expected = "User Successfully Created";
    	actual = userService.registerUser(user);
    	assertEquals(expected, actual);
    }
    
    // Test case to check if user authentication is successful with correct credentials
    @Test
    public void testAuthenticateUserSuccess() {
    	Map<Integer, User> actual = userService.authenticateUser("user001", "password123");
        assertTrue(actual.containsKey(1));
    }
    
    // Test case to check if user authentication fails with incorrect credentials
//    @Test
//    public void testAuthenticateUserFailure() {
//    	Map<Integer, User> actual = userService.authenticateUser("john", "wrongpass");
//    	assertFalse(actual.containsKey(3));
//    }

}

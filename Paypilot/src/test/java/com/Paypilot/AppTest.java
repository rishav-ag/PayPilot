package com.Paypilot;


import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import controller.UserController;
import model.User;
import repo.UserDAO;
import repo.UserRepository;
import service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;


public class AppTest {
    Main main;
    UserController userController;
    User user;
    UserRepository userRepository;
    UserService userService;
   UserDAO userDAO;
    @BeforeClass
    public static void set() throws Exception{
    	
    }
    @Before
    public void setUp() {
    	main = new Main();
    	userController = new UserController();
    	user = new User();
    	userRepository = new UserRepository();
    	userService = new UserService();
    	userDAO=new UserDAO();
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
    	user = new User("user0010", "user0010@example.com", "Password@123", "ABCDE123F7980", "123456789045780", "HDFC0000123", "HDFC");
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
    
    //Test case to check if the user exists
    @Test
    public void testUserExists() {
    	assertTrue(userDAO.userExists("user001"));
  
    }
    
    //Test case to check if the user does not exists
    @Test
    public void testUserDoesNotExist() {
    	assertFalse(userDAO.userExists("user000"));
    }
    
    //Test case to find the user based on the id
    @Test
    public void testFindByUserId() {
    	User expected = new User("user001", "john.doe@example.com", "password123", "ABCDE1234F", "1234567890", "HDFC0000123", "HDFC");
    	String u="user001";
    	User actual=userDAO.findByUserId(u);
    	assertEquals(expected.getUserId(), actual.getUserId());
    }
    
    //Test case to find the user based on the email
    @Test
    public void testFindByUserEmail() {
    	User expected = new User("user001", "john.doe@example.com", "password123", "ABCDE1234F", "1234567890", "HDFC0000123", "HDFC");
    	String e="john.doe@example.com";
    	User actual=userDAO.findByUserEmail(e);
    	assertEquals(expected.getEmail(), actual.getEmail());
    }
    
    //Test case to find the user based on the id or email
    @Test
    public void testFindByUserIdOrEmail() {
    	User expected = new User("user001", "john.doe@example.com", "password123", "ABCDE1234F", "1234567890", "HDFC0000123", "HDFC");
    	String u="user001";
    	String e="john.doe@example.com";
    	User actual=userDAO.findByUserIdOrEmail(e);
    	assertEquals(expected.getEmail(), actual.getEmail());
    }
    
    //Test case to find where the pan already exists
    @Test
    public void testPanExists() {
    	String p="ABCDE1234F";
    	boolean res=userDAO.panExists(p);
    	assertTrue(res);
    }
    
    //Test case to find where the pan does not exists
    @Test
    public void testPanDoesNotExist() {
    	String p="ABCDE1234FG";
    	boolean res=userDAO.panExists(p);
    	assertFalse(res);
    }
    
    //Deleting the data which is used for testing purpose from the database
    @AfterClass
    public static void tearUp() {
    	PreparedStatement st = null;
        Connection con = null;
        String query = null;
         
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
            query ="DELETE FROM users where user_id='user0010'";
            st = con.prepareStatement(query);
            
           st.executeUpdate( );
           st.close();
           con.close();
           
        }
        catch(Exception e) {
        	e.printStackTrace();
        }		
    }
    
}

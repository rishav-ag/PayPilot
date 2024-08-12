package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import model.User;
import service.UserService;

public class UserController {

	    private UserService userService;
	    BufferedReader br;
	    
	    public UserController() {
	    	userService = new UserService();
	    	br = new BufferedReader(new InputStreamReader(System.in));
	    }
	    
	    /***********************************************************************************************************************
		 * Register a new User after checking the pre-conditions.
		 */
	    public void signUp() throws IOException {

	        System.out.print("Enter User ID: ");
	        String userId = br.readLine();

	        System.out.print("Enter Email: ");
	        String email = br.readLine();

	        System.out.print("Enter Password: ");
	        String password = br.readLine();

	        System.out.print("Confirm Password: ");
	        String confirmPassword = br.readLine();

	        while (!password.equals(confirmPassword)) { // checking for password match
	            System.out.print("Passwords do not match. Please try again or press 1 to exit");
	            confirmPassword = br.readLine();
	            if(confirmPassword.equals("1")) {
	            	System.out.println("Exiting......");
	            	return;
	            }
	        }

	        System.out.print("Enter PAN Details: ");
	        String panDetails = br.readLine();

	        System.out.print("Enter Bank Account Number: ");
	        String bankAccountNumber = br.readLine();

	        System.out.print("Enter IFSC Code: ");
	        String ifscCode = br.readLine();

	        System.out.print("Enter Banking Partner: ");
	        String bankingPartner = br.readLine();

	        User newUser = new User(userId, email, password, panDetails, bankAccountNumber, ifscCode, bankingPartner);
	        String result = userService.registerUser(newUser); // saving new user 

	        System.out.println(result);

	        if (result.equals("User Access successfully raised")) {
	            redirectToLoginPage();
	            //after registration, move to login page 
	        }
	    }
	    
	    /***********************************************************************************************************************
		 * Sign in the registered user, if the provided credentials are valid.
		 * Provide the option for Resetting password, Re-attempts.
		 */
	    public void signIn() throws IOException {
	    	System.out.print("Enter User ID/ Email: ");
	    	String userIdOrEmail = br.readLine();
	    	System.out.print("Enter Password: ");
	    	String password = br.readLine();
	    	Map<Integer, User> res = userService.authenticateUser(userIdOrEmail, password);
	    	if(res.containsKey(1)) {
	    		System.out.println("\nWelcome " + res.get(1).getUserId());
	    		return;
	    	} else if(res.containsKey(2)) {
	    		System.out.println("\nInvalid Password. Please try again or press\n1 Exit\n2 Forgot Password?");
	    		System.out.print("Enter Password/Choice: ");
		    	password = br.readLine();
		    	while(!password.equals(res.get(2).getPassword())) {
		    		if(password.equals("1")) { //Exit the application
			    		System.out.println("\nExiting.... Successfully Exited");
			    		return;
			    	}
		    		if(password.equals("2")) { //Change the Password
		    			System.out.print("Enter New Password: ");
		    	        password = br.readLine();

		    	        System.out.print("Confirm New Password: ");
		    	        String confirmPassword = br.readLine();

		    	        while (!password.equals(confirmPassword)) { // checking for password match
		    	            System.out.print("Passwords do not match. Please try again or press 1 to exit: ");
		    	            confirmPassword = br.readLine();
		    	            if(confirmPassword.equals("1")) {
		    	            	System.out.println("\nExiting.... Successfully Exited");
					    		return;
		    	            }
		    	        }
		    	        res.get(2).setPassword(confirmPassword);
		    	        System.out.println("\nPassword successfully reset. Please Re-login to continue.");
		    	        redirectToLoginPage();
		    		} else {
			    		System.out.println("\nInvalid Password. Please try again or press\n1 Exit\n2 Forgot Password?");
			    		System.out.print("Enter Password/Choice: ");
				    	password = br.readLine();
		    		}
		    	}
		    	System.out.println("Welcome " + res.get(2).getUserId());
	    		return;
	    	} else {
	    		System.out.println("Invalid Credentials or User does not exists. Please try again.");
	    		redirectToLoginPage();
	    	}
	    }

	    private void redirectToLoginPage() throws IOException {
	        System.out.println("Redirecting to login page...");
	        signIn();
	    }
	    
	    
	    
	}

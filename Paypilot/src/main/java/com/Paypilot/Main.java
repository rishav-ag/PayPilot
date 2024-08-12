package com.Paypilot;

/**
 * Hello world!
 *
 */
import java.io.*;

import controller.UserController;

public class Main {
	
	/***********************************************************************************
	 * This function is the main function that is the entry point of the application	
	 * @param args
	 * @throws IOException
	 */
    public static void main( String[] args ) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        UserController userController = new UserController();
        System.out.println("*****************Welcome to PayPilot*****************");
        System.out.println("\n\nPlease select the desired option.\n1. Login(for Existing Users)\n2. Sign Up(For new Users)");
        while(true) {
	        int n = Integer.parseInt(br.readLine());
	        switch(n) {
		        case 1 : userController.signIn();
		        		return;
		        case 2 : userController.signUp();
		        		return;
		        default : System.out.println("Invalid Choice. Please re-enter.");
	        }
        }
    }
}





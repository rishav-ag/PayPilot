package repo;

import java.util.HashMap;
import java.util.Map;

import model.User;

public class UserRepository {
 
	    Map<String, User> userDatabase;
	    
	    public UserRepository() {
	    	userDatabase = new HashMap<String, User>();
	    	userDatabase.put("user001", new User("user001", "john.doe@example.com", "password123", "ABCDE1234F", "1234567890", "HDFC0000123", "HDFC"));
	        userDatabase.put("user002", new User("user002", "jane.smith@example.com", "password456", "ABCDE5678G", "0987654321", "ICIC0000456", "ICICI"));
	        userDatabase.put("user003", new User("user003", "david.jones@example.com", "password789", "ABCDE9101H", "1122334455", "SBI0000789", "SBI"));
	        userDatabase.put("user004", new User("user004", "emma.brown@example.com", "password012", "ABCDE2345J", "5566778899", "PNB0001234", "PNB"));
	        userDatabase.put("user005", new User("user005", "michael.white@example.com", "password345", "ABCDE6789K", "6677889900", "BOB0005678", "BOB"));
	    }
	    
		/***********************************************************************************************************************
		 * Saves the user into the database
		 */
	    public void saveUser(User user) {
	        userDatabase.put(user.getUserId(), user);
	    }

		/***********************************************************************************************************************
		 * Checks if the user already exists in the database
		 */
	    public boolean userExists(String userId) {
	        return userDatabase.containsKey(userId);
	    }

		/***********************************************************************************************************************
		 * Finds the user By ID.
		 * Returns User instance, if it exists.
		 * Returns null, if it does not exists.
		 */
	    public User findByUserId(String userId) {
	    	if(userDatabase.containsKey(userId)) {
	    		return userDatabase.get(userId);
	    	} else {
	    		return null;
	    	}
	    }
	    
		/***********************************************************************************************************************
		 * Finds the user By Email.
		 * Returns User instance, if it exists.
		 * Returns null, if it does not exists.
		 */
	    public User findByUserEmail(String email) {
	    	for (User user : userDatabase.values()) {
	            if (user.getEmail().equals(email)) {
	                return user;
	            }
	        }
	        return null;
	    }
	    
	    /***********************************************************************************************************************
		 * Finds the user By User ID or Email.
		 * Returns User instance, if it exists.
		 * Returns null, if it does not exists.
		 */
	    public User findByUserIdOrEmail(String userIdOrEmail) {
	    	if(userDatabase.containsKey(userIdOrEmail)) {
	    		return userDatabase.get(userIdOrEmail);
	    	}
	    	for (User user : userDatabase.values()) {
	            if (user.getEmail().equals(userIdOrEmail)) {
	                return user;
	            }
	        }
	        return null;
	    }
	    
		/***********************************************************************************************************************
		 * Checks if the PAN Details exists or not.
		 */
	   
	    public boolean panExists(String panDetails) {
	        for (User user : userDatabase.values()) {
	            if (user.getPanDetails().equals(panDetails)) {
	                return true;
	            }
	        }
	        return false;
	    }
	     
	}

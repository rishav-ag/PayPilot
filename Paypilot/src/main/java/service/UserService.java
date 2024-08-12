package service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import model.User;
import repo.UserRepository;

public class UserService {

    private UserRepository userRepository;

	    public UserService() {
	        userRepository = new UserRepository();
	    }

		/***********************************************************************************************************************
		 * Registers the new user, if the user with the same UserID, Email & PAN does not already exists.
		 */
	    public String registerUser(User user) {
	        if (userRepository.userExists(user.getUserId())) {
	            return "Account already exists"; // checking userId exists
	        }

	        if (userRepository.findByUserEmail(user.getEmail()) != null) {
	            return "Error: Email is already registered"; // checking email exists
	        }

	        if (!isValidEmail(user.getEmail())) {
	            return "Error: Invalid email format"; // checking valid email format
	        }
	        if (userRepository.panExists(user.getPanDetails())) {
	            return "Error: PAN details are already registered."; // checking pan exists
	        }


	        if (!isValidPassword(user.getPassword())) { // checking password strength
	            return "Password must be at least 8 characters long, include at least one digit, one lowercase letter, one uppercase letter, and one special character.";
	        }

	        userRepository.saveUser(user); // Saving the user
	        return "User Successfully Created";
	    }

		/***********************************************************************************************************************
		 * Validates email, if it satisfies the email validation criteria.
		 */
	    public boolean isValidEmail(String email) {
	        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; //Regular expression for validating an email address format
	        Pattern pattern = Pattern.compile(emailRegex); //Compile the regular expression into a Pattern object
	        return pattern.matcher(email).matches(); //Check if the email matches the compiled regex pattern and return the result
	    }

		/***********************************************************************************************************************
		 * Validates password, if it satisfies the password validation criteria.
		 */
	    public boolean isValidPassword(String password) {
	    	// Regular expression for validating a strong password format
	        String passwordRegex =
	                "^(?=.*[0-9])" +       // at least one digit
	                "(?=.*[a-z])" +        // at least one lowercase letter
	                "(?=.*[A-Z])" +        // at least one uppercase letter
	                "(?=.*[@#$%^&+=])" +   // at least one special character
	                "(?=\\S+$).{8,}$";     // no whitespace allowed and at least 8 characters

	        Pattern pattern = Pattern.compile(passwordRegex); //Compile the regular expression into a Pattern object
	        return pattern.matcher(password).matches(); //Check if the password matches the compiled regex pattern and return the result
	    }
	    
	    /***********************************************************************************************************************
		 * Checks if the provided userId/Email exists and the provided password matches with the database.
		 * Returns 1 of the following.
		 * 1 - Successfully verified
		 * 2 - Found the User but password does not matches
		 * 3 - Invalid UserId/Email
		 */
	    public Map<Integer, User> authenticateUser(String userIdOrEmail, String password) {
	    	Map<Integer, User> res = new HashMap<Integer, User>();
	    	User user = userRepository.findByUserIdOrEmail(userIdOrEmail);
	    	if(user != null) {
	    		if(password.equals(user.getPassword())) {
	    			res.put(1, user);
	    		}
	    		res.put(2, user);
	    	} else {
	    		res.put(3, null);
	    	}
	    	return res;
	    }
	}

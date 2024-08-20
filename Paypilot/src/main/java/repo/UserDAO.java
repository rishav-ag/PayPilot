package repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

public class UserDAO {
	   public void saveUser(User user) {
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="INSERT INTO users VALUES(?,?,?,?,?,?,?)";
	            st = con.prepareStatement(query);
	             st.setString(1, user.getUserId());
	             st.setString(2,user.getEmail() );
	             st.setString( 3,user.getPassword());
	             st.setString( 4,user.getPanDetails());
	             st.setString( 5,user.getBankAccountNumber());
	             st.setString( 6,user.getIfscCode());
	             st.setString( 7,user.getBankingPartner());
	           st.executeUpdate( );
	           st.close();	
	           con.close();
	           
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }		   
	   }  
	   public boolean userExists(String userId) {
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="SELECT * FROM users WHERE user_id =?";
	            st = con.prepareStatement(query);
	             st.setString(1, userId);    
	           rs = st.executeQuery( );
	           if (rs.next()) {
	        	   return true;
	           }
	           rs.close();
	           st.close();
	           con.close();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }		   
	        return false;
	   }
	   public User findByUserId(String userId) {
		   User user = null;
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="SELECT * FROM users WHERE user_id =?";
	            st = con.prepareStatement(query);
	             st.setString(1, userId);    
	           rs = st.executeQuery( );
	           if (rs.next()) {
	        	     userId= rs.getString(1);
	        	      String email= rs.getString(2);
	        	      String password= rs.getString(3);
	        	      String panDetails= rs.getString(4);
	        	      String bankAccountNumber= rs.getString(5);
	        	      String ifscCode= rs.getString(6);
	        	      String bankingPartner= rs.getString(7);
	        	   user = new User(userId, email,  password,panDetails,  bankAccountNumber,
	        				 ifscCode,bankingPartner);
	        	   return user;
	           }
	           rs.close();
	           st.close();
	           con.close();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }		   
		   return user;
	   }
	   public User findByUserEmail(String email) {
		   User user = null;
		   
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="SELECT * FROM users WHERE email =?";
	            st = con.prepareStatement(query);
	             st.setString(1, email);    
	           rs = st.executeQuery( );
	           if (rs.next()) {
	        	     String userId= rs.getString(1);
	        	       email= rs.getString(2);
	        	      String password= rs.getString(3);
	        	      String panDetails= rs.getString(4);
	        	      String bankAccountNumber= rs.getString(5);
	        	      String ifscCode= rs.getString(6);
	        	      String bankingPartner= rs.getString(7);
	        	   user = new User(userId, email,  password,panDetails,  bankAccountNumber,
	        				 ifscCode,bankingPartner);
	        	   return user;
	           }
	           rs.close();
	           st.close();
	           con.close();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }		   
		   
		   return user;
	   }
	   public User findByUserIdOrEmail(String userIdOrEmail) {
		   User user = null;
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="SELECT * FROM users WHERE email =? OR user_id =?";
	            st = con.prepareStatement(query);
	             st.setString(1, userIdOrEmail); 
	             st.setString(2, userIdOrEmail); 
	           rs = st.executeQuery( );
	           if (rs.next()) {
	        	     String userId= rs.getString(1);
	        	      String  email= rs.getString(2);
	        	      String password= rs.getString(3);
	        	      String panDetails= rs.getString(4);
	        	      String bankAccountNumber= rs.getString(5);
	        	      String ifscCode= rs.getString(6);
	        	      String bankingPartner= rs.getString(7);
	        	   user = new User(userId, email,  password,panDetails,  bankAccountNumber,
	        				 ifscCode,bankingPartner);
	        	   return user;
	           }
	           rs.close();
	           st.close();
	           con.close();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }		   
		   return user;
	   }
	   public boolean panExists(String panDetails) {
		   PreparedStatement st = null;
	        Connection con = null;
	        String query = null;
	         ResultSet rs = null;
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234");
	            query ="SELECT * FROM users WHERE pan_details=?";
	            st = con.prepareStatement(query);
	             st.setString(1, panDetails); 
	           rs = st.executeQuery( );
	           if (rs.next()) {
	        	   return true;
	           }
	           rs.close();
	           st.close();
	           con.close();
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }	
		   return false;
	   }
}

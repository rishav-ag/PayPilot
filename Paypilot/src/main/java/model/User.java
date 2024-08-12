package model;

public class User {

    private String userId;
    private String email;
    private String password;
    private String panDetails;
    private String bankAccountNumber;
    private String ifscCode;
    private String bankingPartner;
   
    public User() {}
    //Parameterized Constructor 
    public User(String userId, String email, String password, String panDetails, String bankAccountNumber,
			String ifscCode, String bankingPartner) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.panDetails = panDetails;
		this.bankAccountNumber = bankAccountNumber;
		this.ifscCode = ifscCode;
		this.bankingPartner = bankingPartner;
		 
	}
    
	// Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPanDetails() {
        return panDetails;
    }

    public void setPanDetails(String panDetails) {
        this.panDetails = panDetails;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBankingPartner() {
        return bankingPartner;
    }

    public void setBankingPartner(String bankingPartner) {
        this.bankingPartner = bankingPartner;
    }
    
}

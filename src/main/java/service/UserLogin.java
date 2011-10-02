package service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import domain.User;

public class UserLogin {
	
    protected final Log logger = LogFactory.getLog(getClass());
	private String userName;
	private String password;
	private String user_password_error;
	private User user;
	
	public void setUser(User user) {
		this.user = user;
		this.user.setUserName(userName);
		this.user.setPassword(password);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser_password_error(String user_password_error) {
		this.user_password_error = user_password_error;
	}
	public String getUser_password_error() {
		return user_password_error;
	}
	
	public void setUserName(String userName) {
		logger.info("Set userName = " + userName);
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String password) {
		logger.info("Set password = " + password);
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
}

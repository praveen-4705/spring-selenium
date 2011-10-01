package service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserLogin {
	
    protected final Log logger = LogFactory.getLog(getClass());
	private String userName;
	private String password;
	
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

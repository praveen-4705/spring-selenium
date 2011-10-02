package service.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.User;

import service.UserLogin;
import service.UserManager;

public class LoginValidator implements Validator{

	private UserManager userManager;
    protected final Log logger = LogFactory.getLog(getClass());

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public boolean supports(Class clazz) {
		return UserLogin.class.equals(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		UserLogin userLogin = (UserLogin) target;
		userLogin.setUser(new User());
		boolean validUser = userManager.isValidUser(userLogin.getUser());
		logger.info("Validating User Login");
		if(userLogin.getUserName() == null || "".equals(userLogin.getUserName())){
            errors.rejectValue("userName", null, "Please enter username");
            logger.info("UserName not given");

		}
		if(userLogin.getPassword() == null || "".equals(userLogin.getPassword())){
            errors.rejectValue("password", null, "Please enter password");
            logger.info("Password not given");
		}
		if(!validUser){
			errors.rejectValue("user_password_error", null, "Please enter a valid UserName and password");
			logger.info("User doesn't exist");
		}
		
	}

}

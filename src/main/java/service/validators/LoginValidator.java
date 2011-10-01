package service.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import service.UserLogin;

public class LoginValidator implements Validator{

	public boolean supports(Class clazz) {
		return UserLogin.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		UserLogin userLogin = (UserLogin) target;
		if(userLogin == null){
            errors.rejectValue("usercomplete", "Please enter username and password");
		}else if(userLogin.getUserName() == null || "".equals(userLogin.getUserName())){
            errors.rejectValue("userName", "Please enter username");

		}else if(userLogin.getPassword() == null || "".equals(userLogin.getPassword())){
            errors.rejectValue("password", "Please enter password");

		}
		
	}

}

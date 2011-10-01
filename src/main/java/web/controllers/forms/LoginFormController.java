package web.controllers.forms;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.UserLogin;
import service.UserManager;
import domain.User;

public class LoginFormController extends SimpleFormController{
	
	private UserManager userManager;
    protected final Log logger = LogFactory.getLog(getClass());
    
    public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
    
    public ModelAndView onSubmit(Object command) throws ServletException {
    	boolean validUser = false;
    	UserLogin userLogin = ((UserLogin) command);
    	User user = new User();
    	user.setUserName(userLogin.getUserName());
    	user.setPassword(userLogin.getPassword());
    	logger.info("Valitade existence of User: " +user.toString());
    	validUser = userManager.isValidUser(user);   	
    	if(validUser){
    		logger.info(user.toString() + " is a valid user");
    		return new ModelAndView(new RedirectView(getSuccessView()));
    	}else{
    		logger.info(user.toString() + " is not a valid user");
    		return new ModelAndView(new RedirectView("login.htm"));
    	}
    }
}

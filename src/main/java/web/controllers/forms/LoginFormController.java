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
	
    protected final Log logger = LogFactory.getLog(getClass());   
    
    public ModelAndView onSubmit(Object command) throws ServletException {
    	    UserLogin userLogin = (UserLogin) command;    	    
    		logger.info(userLogin.getUserName() + " Is a valid User");
    		logger.info("Redirecting to " + getSuccessView());
    		return new ModelAndView(new RedirectView(getSuccessView()));
    }
}

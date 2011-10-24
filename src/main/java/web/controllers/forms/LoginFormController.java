package web.controllers.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.UserLogin;

public class LoginFormController extends SimpleFormController{
	
    protected final Log logger = LogFactory.getLog(getClass());   
    
    public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,Object command,BindException errors) throws ServletException {
    	    HttpSession session = request.getSession();
    		UserLogin userLogin = (UserLogin) command;    	    
    		session.setAttribute("userInfo",userLogin );	    		
    		logger.info(userLogin.getUserName() + " Is a valid User");    		
    		logger.info("Redirecting to " + getSuccessView());
    		return new ModelAndView(new RedirectView(getSuccessView(),true));
    }    
}

package web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.jetty.jetty.servlet.AbstractSessionManager.Session;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.ProductManager;

public class HomeController extends MultiActionController{
	
    protected final Log logger = LogFactory.getLog(getClass());
	
	private ProductManager productManager;
	
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	public ProductManager getProductManager() {
		return productManager;
	}
	
	public ModelAndView home(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		boolean hasPriv = session.getAttribute("userInfo") != null;
		Map<String, Object> myModel = new HashMap<String, Object>();		
		logger.info("Calling home method");		
		myModel.put("products", productManager.getProducts());
		myModel.put("hasPriv", hasPriv);
		
        return new ModelAndView("web/home/home.jsp", "model", myModel);

	}
	
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Calling about method");		

		return new ModelAndView("web/home/about.jsp");
	}
	
	public ModelAndView logOut(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Login Out");		
		HttpSession session = request.getSession();
		session.removeAttribute("userInfo");
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("products", productManager.getProducts());
		return new ModelAndView("web/home/home.jsp","model",myModel);
	}


}

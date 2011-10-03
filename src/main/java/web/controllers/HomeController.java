package web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
		
		Map<String, Object> myModel = new HashMap<String, Object>();		
		logger.info("Calling home method");		
		myModel.put("products", productManager.getProducts());
		
        return new ModelAndView("web/home/home.jsp", "model", myModel);

	}
	
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("Calling about method");		

		return new ModelAndView("web/home/about.jsp");
	}

}

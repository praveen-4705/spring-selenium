package web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import domain.Product;

import service.ProductManager;

public class InventoryController extends MultiActionController {

    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;

    public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
    
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    	Map<String, Object> myModel = new HashMap<String, Object>();
    	
        String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);

        myModel.put("now", now);
        myModel.put("products", productManager.getProducts());
        
        return new ModelAndView("web/inventory/home.jsp", "model", myModel);
    }
    
    public ModelAndView priceIncrease(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	return new ModelAndView(new RedirectView("priceIncrease"));
    }
    
    public ModelAndView newProduct(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	return new ModelAndView(new RedirectView("newProduct"));
    }

    public ModelAndView view(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	int productId = Integer.parseInt(request.getParameter("id"));
    	logger.info("Get productId = " + productId);
    	Product product = productManager.getById(productId);
    	logger.info("Get Product = " + product.toString());
    	return new ModelAndView(new RedirectView("home"));
    }
    
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	int productId = Integer.parseInt(request.getParameter("id"));
    	logger.info("Get productId = " + productId);    	
    	
    	return new ModelAndView(new RedirectView("home"));
    }
    
    public ModelAndView destroy(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
    	int productId = Integer.parseInt(request.getParameter("id"));
    	logger.info("Get productId = " + productId);
    	if(productManager.destroy(productId))
    		logger.info("Delete product with ID = " + productId);
    	else
    		logger.info("Couldn't delete product with Id = " + productId);
    	
    	return new ModelAndView(new RedirectView("home"));
    }
    
}
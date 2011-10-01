package web.controllers;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.PriceIncrease;
import service.ProductManager;

public class LoginController extends SimpleFormController{
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private ProductManager productManager;

    public void setProductManager(ProductManager productManager) {
    	this.productManager = productManager;
    }
    
    public ProductManager getProductManager() {
    	return productManager;
    }
    
    public ModelAndView onSubmit(Object command) throws ServletException {

        int increase = ((PriceIncrease) command).getPercentage();
        
        logger.info("Increasing prices by " + increase + "%.");

        productManager.increasePrice(increase);

        logger.info("returning from PriceIncreaseForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }

	
}

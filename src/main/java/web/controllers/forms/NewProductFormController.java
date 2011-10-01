package web.controllers.forms;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import repository.JdbcProductDao;
import service.ProductManager;
import domain.Product;

public class NewProductFormController extends SimpleFormController{

    protected final Log logger = LogFactory.getLog(getClass());
    
    private ProductManager productManager;
    
    public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
    
    public ProductManager getProductManager() {
		return productManager;
	}
    
    public ModelAndView onSubmit(Object command) throws ServletException {
    	Product newProduct = ((Product) command);
    	logger.info("Saving new Product: " + newProduct.toString());
    	productManager.newProduct(newProduct);    	
        return new ModelAndView(new RedirectView(getSuccessView()));
    }	
}

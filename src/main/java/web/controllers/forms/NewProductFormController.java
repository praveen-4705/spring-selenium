package web.controllers.forms;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import repository.implementation.JdbcProductDao;
import service.CreateProduct;
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
    	CreateProduct createProduct = (CreateProduct) command;
    	createProduct.setProduct(new Product());    	
    	logger.info("Saving new Product: " + createProduct.getProduct().toString());
    	productManager.newProduct(createProduct.getProduct());    	
        return new ModelAndView(new RedirectView(getSuccessView()));
    }	
}

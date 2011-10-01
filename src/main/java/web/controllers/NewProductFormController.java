package web.controllers;

import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import repository.JdbcProductDao;
import domain.Product;

public class NewProductFormController extends SimpleFormController{

    protected final Log logger = LogFactory.getLog(getClass());
    
    private JdbcProductDao jdbcProductDao;

    public void setJdbcProductDao(JdbcProductDao jdbcProductDao) {
		this.jdbcProductDao = jdbcProductDao;
	}
    public JdbcProductDao getJdbcProductDao() {
		return jdbcProductDao;
	}
    
    public ModelAndView onSubmit(Object command) throws ServletException {
    	Product newProduct = ((Product) command);
    	logger.info("Saving new Product: " + newProduct.toString());
    	jdbcProductDao.newProduct(newProduct);    	
        return new ModelAndView(new RedirectView(getSuccessView()));
    }	
}

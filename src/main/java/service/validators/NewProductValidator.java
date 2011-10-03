package service.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import service.CreateProduct;
import service.ProductManager;
import domain.Product;

public class NewProductValidator implements Validator{

    protected final Log logger = LogFactory.getLog(getClass());
    private ProductManager productManager;
    
    public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
    
	public boolean supports(Class clazz) {
		return CreateProduct.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		CreateProduct createProduct = (CreateProduct) target;
		createProduct.setProduct(new Product());
		if(createProduct.getDescription() == null || createProduct.getDescription().isEmpty()){
			errors.rejectValue("description", null, "Please enter a Product Description");
			logger.info("No Product Description Added");
		}
		if(productManager.productExist(createProduct.getProduct())){
			errors.rejectValue("description_price_error", null , "Product already exist!");
			logger.info(createProduct.getProduct().toString() + " Alreadt Exist!");
		}
		if(createProduct.getPrice() == 0){
			errors.rejectValue("price", null, "Please enter a Product Price > 0");
			logger.info("Price should be > 0");
		}
	}

}

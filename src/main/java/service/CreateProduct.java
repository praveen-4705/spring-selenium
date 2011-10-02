package service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import domain.Product;

public class CreateProduct {
	private String description;
	private double price;
	private String description_price_error;
	private Product product;
	
    protected final Log logger = LogFactory.getLog(getClass());
    
    public void setProduct(Product product) {
    	product.setDescription(description);
    	product.setPrice(price);
		this.product = product;
	}
    public Product getProduct() {
		return product;
	}
    
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	
	public void setDescription_price_error(String description_price_error) {
		this.description_price_error = description_price_error;
	}
	public String getDescription_price_error() {
		return description_price_error;
	}
	
}

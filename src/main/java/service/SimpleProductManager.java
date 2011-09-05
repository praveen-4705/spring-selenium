package service;

import java.util.List;

import domain.Product;

public class SimpleProductManager implements ProductManager{

    private List<Product> products;
	 
    
	public void increasePrice(int percentage) {
		// TODO Auto-generated method stub
		
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

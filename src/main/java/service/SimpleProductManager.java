package service;

import java.util.List;

import repository.ProductDao;
import domain.Product;

public class SimpleProductManager implements ProductManager{

//    private List<Product> products;
    private ProductDao productDao;
    
    public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    
    public ProductDao getProductDao() {
		return productDao;
	}
	 
    
	public void increasePrice(int percentage) {
		List<Product> products = productDao.getProductList();
		if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDao.saveProduct(product);
            }
        }
		
	}

	public List<Product> getProducts() {
		return productDao.getProductList();
	}

//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
}

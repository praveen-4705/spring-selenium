package service.implementation;

import java.util.List;

import repository.ProductDao;
import service.ProductManager;
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

	public void newProduct(Product product) {
		productDao.newProduct(product);
	}

	public boolean productExist(Product product) {
		boolean exist = false;
		List<Product> products = productDao.getProductList();
		for(Product p : products){
			if(p.getDescription().equals(product.getDescription())){
				exist = true;
				break;
			}
		}
		return exist;
	}
}

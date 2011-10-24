package repository;

import java.util.List;

import domain.Product;

public interface ProductDao {

    public List<Product> getProductList();

    public void saveProduct(Product prod);
    
    public void newProduct(Product prod);
    
	public Product getById(long id);
	
	public boolean destroy(Product product);
    
    public boolean destroy(long id);
}
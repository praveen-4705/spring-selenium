package service;

import java.io.Serializable;
import java.util.List;

import domain.Product;

public interface ProductManager extends Serializable{

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();
    
    public void newProduct(Product product);
    
    public boolean productExist(Product product);

    public Product getById(long id);
    
    public boolean destroy(Product product);
    
    public boolean destroy(long id);
}

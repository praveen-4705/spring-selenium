package repository.inMemory;

import java.util.List;

import repository.ProductDao;

import domain.Product;

public class InMemoryProductDao implements ProductDao{

    private List<Product> productList;
    
    public InMemoryProductDao(List<Product> productList) {
        this.productList = productList;
    }
    
	public List<Product> getProductList() {
		return productList;
	}

	public void saveProduct(Product prod) {

		
	}

	public void newProduct(Product prod) {
		productList.add(prod);
	}

}

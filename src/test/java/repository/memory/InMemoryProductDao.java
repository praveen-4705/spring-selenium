package repository.memory;

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

	public Product getById(long userId) {
		Product product = null;
		
		for(Product p : productList){
			if(p.getId() == userId)
				product = p;
		}
		
		return product;
	}

	public boolean destroy(Product product) {
		boolean response = false;
		for(int i = 0 ; i < productList.size() ; i++){
			if(productList.get(i).getDescription() == product.getDescription()){
				productList.remove(i);
				response = true;
				break;
			}
		}
		return response;
	}

	public boolean destroy(long id) {
		boolean response = false;
		for(int i = 0 ; i < productList.size() ; i++){
			if(productList.get(i).getId() == id){
				productList.remove(i);
				response = true;
				break;
			}
		}
		return response;
	}

}

package repository.implementation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import repository.ProductDao;

import domain.Product;
import domain.User;

public class JdbcProductDaoTest extends AbstractTransactionalDataSourceSpringContextTests{
    private ProductDao productDao;
    protected final Log logger = LogFactory.getLog(getClass());

    public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] {"test-context.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {    	
        super.deleteFromTables(new String[] {"products"});
        super.executeSqlScript("file:src/main/db/load_data.sql", true);
    }

    public void testGetProductList() {
        
        List<Product> products = productDao.getProductList();
        
        assertEquals("wrong number of products?", 3, products.size());
        
    }
    
    public void testSaveProduct() {
        
        List<Product> products = productDao.getProductList();
        
        for (Product p : products) {
            p.setPrice(200.12);
            productDao.saveProduct(p);
        }
        
        List<Product> updatedProducts = productDao.getProductList();
        for (Product p : updatedProducts) {
            assertEquals("wrong price of product?", 200.12, p.getPrice());
        }

    }
    
    public void testNewProduct(){
    	
    	List<Product> productsOld = productDao.getProductList();
    	
        Product p = new Product();
    	p.setDescription("macbook");
    	p.setPrice(999.99);
    	
    	productDao.newProduct(p);
    	
        List<Product> productsNew = productDao.getProductList();
        assertEquals("wrong number of products?", productsOld.size() + 1, productsNew.size());
    }
    
    public void testGetById(){
    	Product invalidProduct = new Product();
    	invalidProduct.setId(4);
    	assertNull("No product, this should be null",productDao.getById(invalidProduct.getId()));
    	Product validProduct = productDao.getProductList().get(0);
    	Product dbProduct = productDao.getById(validProduct.getId());
    	assertEquals("Users should be equeals", validProduct.getId(), dbProduct.getId());
    }
    
    public void testDestroy(){
    	List<Product> productsOld = productDao.getProductList();
    	Product product = productsOld.get(0);
    	boolean deleted = productDao.destroy(product);
    	assertTrue("User is deleted thus this should be true", deleted);
    	List<Product> productsNew = productDao.getProductList();
    	assertEquals("List should be equals", productsOld.size() - 1 , productsNew.size());
    }
    
    public void testDestroyWithId(){
    	List<Product> productsOld = productDao.getProductList();
    	Product product = productsOld.get(0);
    	boolean deleted = productDao.destroy(product.getId());
    	assertTrue("User is deleted thus this should be true", deleted);
    	List<Product> productsNew = productDao.getProductList();
    	assertEquals("List should be equals", productsOld.size() - 1 , productsNew.size());
    }
}

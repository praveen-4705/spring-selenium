package repository.implementation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import repository.ProductDao;
import domain.Product;

@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class JdbcProductDaoTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
    private ProductDao productDao;
    protected final Log logger = LogFactory.getLog(getClass());

    public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    
    @BeforeMethod
    protected void onSetUpInTransaction() throws Exception {    	
        super.deleteFromTables(new String[] {"products"});
        super.executeSqlScript("file:src/main/db/load_data.sql", true);
    }

    @Test	
	public void testGetProductList() {        
        List<Product> products = productDao.getProductList();        
        AssertJUnit.assertEquals("wrong number of products?", 3, products.size());        
    }
    
    @Test
	@Rollback(true)
	public void testSaveProduct() {        
        List<Product> products = productDao.getProductList();        
        for (Product p : products) {
            p.setPrice(200.12);
            productDao.saveProduct(p);
        }        
        List<Product> updatedProducts = productDao.getProductList();
        for (Product p : updatedProducts) {
            AssertJUnit.assertEquals("wrong price of product?", 200.12, p.getPrice());
        }
    }
    
    @Test
	@Rollback(true)
	public void testNewProduct(){    	
    	List<Product> productsOld = productDao.getProductList();    	
        Product p = new Product();
    	p.setDescription("macbook");
    	p.setPrice(999.99);    	
    	productDao.newProduct(p);    	
        List<Product> productsNew = productDao.getProductList();
        AssertJUnit.assertEquals("wrong number of products?", productsOld.size() + 1, productsNew.size());
    }
    
    @Test
	public void testGetById(){
    	Product invalidProduct = new Product();
    	invalidProduct.setId(4);
    	AssertJUnit.assertNull("No product, this should be null",productDao.getById(invalidProduct.getId()));
    	Product validProduct = productDao.getProductList().get(0);
    	Product dbProduct = productDao.getById(validProduct.getId());
    	AssertJUnit.assertEquals("Users should be equeals", validProduct.getId(), dbProduct.getId());
    }
    
    @Test
	@Rollback(true)
	public void testDestroy(){
    	List<Product> productsOld = productDao.getProductList();
    	Product product = productsOld.get(0);
    	boolean deleted = productDao.destroy(product);
    	AssertJUnit.assertTrue("User is deleted thus this should be true", deleted);
    	List<Product> productsNew = productDao.getProductList();
    	AssertJUnit.assertEquals("List should be equals", productsOld.size() - 1 , productsNew.size());
    }
    
    @Test
	@Rollback(true)
	public void testDestroyWithId(){
    	List<Product> productsOld = productDao.getProductList();
    	Product product = productsOld.get(0);
    	boolean deleted = productDao.destroy(product.getId());
    	AssertJUnit.assertTrue("User is deleted thus this should be true", deleted);
    	List<Product> productsNew = productDao.getProductList();
    	AssertJUnit.assertEquals("List should be equals", productsOld.size() - 1 , productsNew.size());
    }
}
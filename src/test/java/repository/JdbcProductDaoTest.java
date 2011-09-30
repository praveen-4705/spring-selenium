package repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import domain.Product;

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
}
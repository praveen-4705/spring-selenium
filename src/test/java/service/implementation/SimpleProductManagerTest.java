package service.implementation;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import repository.ProductDao;
import repository.inMemory.InMemoryProductDao;
import domain.Product;
import domain.User;

public class SimpleProductManagerTest extends TestCase{
	private SimpleProductManager productManager;
	private List<Product> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10); 
    
    private static int POSITIVE_PRICE_INCREASE = 10;
    
    protected void setUp() throws Exception {
        productManager = new SimpleProductManager();
        products = new ArrayList<Product>();
        
        // stub up a list of products
        Product product = new Product();
        product.setDescription(CHAIR_DESCRIPTION);
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription(TABLE_DESCRIPTION);
        product.setPrice(TABLE_PRICE);
        products.add(product);
        
        ProductDao productDao = new InMemoryProductDao(products);
        productManager.setProductDao(productDao);
//        productManager.setProducts(products);
        
    }

    public void testGetProductsWithNoProducts() {
        productManager = new SimpleProductManager();
        productManager.setProductDao(new InMemoryProductDao(null));
        assertNull(productManager.getProducts());
    }
    
    public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        assertNotNull(products);        
        assertEquals(PRODUCT_COUNT, productManager.getProducts().size());
    
        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());      
    }
    
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProductDao(new InMemoryProductDao(null));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }
    
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            fail("Products list is empty.");
        }           
    }
    
    public void testIncreasePriceWithPositivePercentage() {
        productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productManager.getProducts();      
        Product product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());

        product = products.get(1);      
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    }
    
    public void testProducExist(){
    	Product product = new Product();
        product.setDescription(CHAIR_DESCRIPTION);
        product.setPrice(CHAIR_PRICE);
        assertTrue("Product Exist thus test should be true", productManager.productExist(product));
    }
    
    public void testGetById(){
    	Product invalidProduct = new Product();
    	invalidProduct.setId(4);
    	assertNull("No product, this should be null",productManager.getById(invalidProduct.getId()));
    	Product validProduct = productManager.getProducts().get(0);
    	Product dbProduct = productManager.getById(validProduct.getId());
    	assertEquals("Users should be equeals", validProduct.getId(), dbProduct.getId());
    }
    
    public void testDestroy(){
    	List<Product> productsOld = productManager.getProducts();
    	int currSize = productsOld.size();
    	Product product = productsOld.get(0);
    	boolean deleted = productManager.destroy(product);
    	assertTrue("User is deleted thus this should be true", deleted);
    	assertEquals("List should be equals", productsOld.size() , currSize -1 );
    }
    
    public void testDestroyWithId(){
    	List<Product> productsOld = productManager.getProducts();
    	int currSize = productsOld.size();
    	Product product = productsOld.get(0);
    	boolean deleted = productManager.destroy(product.getId());
    	assertTrue("User is deleted thus this should be true", deleted);
    	assertEquals("List should be equals", productsOld.size() , currSize -1 );
    }
}

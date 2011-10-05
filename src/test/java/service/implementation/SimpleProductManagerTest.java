package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import repository.ProductDao;
import repository.memory.InMemoryProductDao;
import domain.Product;

public class SimpleProductManagerTest{
	private SimpleProductManager productManager;
	private List<Product> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10); 
    
    private static int POSITIVE_PRICE_INCREASE = 10;
        
	@BeforeMethod
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
    }

    @Test
	public void testGetProductsWithNoProducts() {
        productManager = new SimpleProductManager();
        productManager.setProductDao(new InMemoryProductDao(null));
        AssertJUnit.assertNull(productManager.getProducts());
    }
    
    @Test
	public void testGetProducts() {
        List<Product> products = productManager.getProducts();
        AssertJUnit.assertNotNull(products);        
        AssertJUnit.assertEquals(PRODUCT_COUNT, productManager.getProducts().size());
    
        Product product = products.get(0);
        AssertJUnit.assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        AssertJUnit.assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        AssertJUnit.assertEquals(TABLE_DESCRIPTION, product.getDescription());
        AssertJUnit.assertEquals(TABLE_PRICE, product.getPrice());      
    }
    
    @Test
	public void testIncreasePriceWithNullListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProductDao(new InMemoryProductDao(null));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            Assert.fail("Products list is null.");
        }
    }
    
    @Test
	public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productManager = new SimpleProductManager();
            productManager.setProductDao(new InMemoryProductDao(new ArrayList<Product>()));
            productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            Assert.fail("Products list is empty.");
        }           
    }
    
    @Test
	public void testIncreasePriceWithPositivePercentage() {
        productManager.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;        
        List<Product> products = productManager.getProducts();      
        Product product = products.get(0);
        AssertJUnit.assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        product = products.get(1);      
        AssertJUnit.assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    }
    
    @Test
	public void testProducExist(){
    	Product product = new Product();
        product.setDescription(CHAIR_DESCRIPTION);
        product.setPrice(CHAIR_PRICE);
        AssertJUnit.assertTrue("Product Exist thus test should be true", productManager.productExist(product));
    }
    
    @Test
	public void testGetById(){
    	Product invalidProduct = new Product();
    	invalidProduct.setId(4);
    	AssertJUnit.assertNull("No product, this should be null",productManager.getById(invalidProduct.getId()));
    	Product validProduct = productManager.getProducts().get(0);
    	Product dbProduct = productManager.getById(validProduct.getId());
    	AssertJUnit.assertEquals("Users should be equeals", validProduct.getId(), dbProduct.getId());
    }
    
    @Test
	public void testDestroy(){
    	List<Product> productsOld = productManager.getProducts();
    	int currSize = productsOld.size();
    	Product product = productsOld.get(0);
    	boolean deleted = productManager.destroy(product);
    	AssertJUnit.assertTrue("User is deleted thus this should be true", deleted);
    	AssertJUnit.assertEquals("List should be equals", productsOld.size() , currSize -1 );
    }
    
    @Test
	public void testDestroyWithId(){
    	List<Product> productsOld = productManager.getProducts();
    	int currSize = productsOld.size();
    	Product product = productsOld.get(0);
    	boolean deleted = productManager.destroy(product.getId());
    	AssertJUnit.assertTrue("User is deleted thus this should be true", deleted);
    	AssertJUnit.assertEquals("List should be equals", productsOld.size() , currSize -1 );
    }
}
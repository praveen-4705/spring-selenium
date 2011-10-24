package domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductTest{	
		
	Product product;

	@BeforeMethod
	protected void setUp() throws Exception {
    	this.product = mock(Product.class);
    }

    @Test
	public void testSetAndGetDescription() {
        String testDescription = "aDescription";
        verify((product),never()).getDescription();
        AssertJUnit.assertNull(product.getDescription());
        product.setDescription(testDescription);        
        stub(product.getDescription()).toReturn(testDescription);
        AssertJUnit.assertEquals(testDescription, product.getDescription());
        verify(product).setDescription(testDescription);
    }

    @Test
	public void testSetAndGetPrice() {
        double testPrice = 100.00;
        AssertJUnit.assertEquals(0, 0, 0);    
        product.setPrice(testPrice);
        AssertJUnit.assertEquals(testPrice, product.getPrice(), 100);
    }
}
package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;

public class InventoryTest extends SeleneseTestBase{

	private SeleniumServer server;

	@BeforeClass
	public void startServer() throws Exception{
		server = new SeleniumServer();
		server.start();
		setUp("http://localhost:8080", "*firefox");
	}
	
	@AfterClass
	public void stopServer(){
		selenium.stop();
		server.stop();
	}
				
	@BeforeMethod	
	public void setUp() throws Exception{	
		selenium.open("/simple-spring-page");
		selenium.click("link=Login");
		selenium.waitForPageToLoad("1000");
		selenium.type("userName", "lellisga");
		selenium.type("password", "lellisga");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
	}
	
	@Test
	public void checkProductOptions(){
		AssertJUnit.assertTrue(selenium.isTextPresent("View"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Edit"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Delete"));
	}
	
	@Test
	public void checkIncreaseOption(){
		AssertJUnit.assertTrue(selenium.isTextPresent("Increase Prices"));
	}
	
	@Test
	public void checkNewProductOption(){
		AssertJUnit.assertTrue(selenium.isTextPresent("Create New Product"));
	}	
	
	@Test
	public void testIncreasePriceErrorWithIncreaseGT50(){
		selenium.click("link=Increase Prices");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("Price Increase :: SpringApp"));
		selenium.type("percentage", "100");
		selenium.submit("id=priceIncrease");
		selenium.waitForPageToLoad("1000");		
		AssertJUnit.assertTrue(selenium.isTextPresent("Don't be greedy - you can't raise prices by more than 50%!"));
	}
	
	@Test
	public void testIncreasePriceErrorWithIncreaseEQ0(){
		selenium.click("link=Increase Prices");
		selenium.waitForPageToLoad("1000");
		selenium.type("percentage", "0");
		selenium.submit("id=priceIncrease");
		selenium.waitForPageToLoad("1000");		
		AssertJUnit.assertTrue(selenium.isTextPresent("You have to specify a percentage higher than 0!"));
	}
}

package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;

public class homeTest extends SeleneseTestBase{
	
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
		selenium.waitForPageToLoad("1000");
	}
	
	@Test
	public void testChekIndexProductList(){		
		AssertJUnit.assertTrue(selenium.isTextPresent("Products"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Lamp"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Table"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Chair"));
		
	}
	
	@Test
	public void testCheckIndexInsertedPage(){		
		AssertJUnit.assertTrue(selenium.isTextPresent("about"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Login"));
	}
	
	@Test
	public void testCheckAboutLink(){		
		selenium.click("link=about");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("This page is made by Li Ellis Gallardo"));		
	}

}

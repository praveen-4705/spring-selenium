package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sun.print.resources.serviceui;

import com.thoughtworks.selenium.SeleneseTestCase;

public class homeTest extends SeleneseTestCase{
	
	private SeleniumServer server;
	
	@BeforeClass
	public void startServer() throws Exception{
		server = new SeleniumServer();
		server.start();
	}
	
	@AfterClass
	public void stopServer(){
		server.stop();
	}
	
	@AfterMethod
	public void stopSeleniumInstance(){
		selenium.stop();
	}
		
	@BeforeMethod	
	public void setUp() throws Exception{	
		setUp("http://localhost:8080", "*firefox");
		selenium.open("/simple-spring-page");
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
		AssertJUnit.assertTrue(selenium.isTextPresent("This page is made by Li Ellis Gallardo"));		
	}

}

package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;

public class loginTest extends SeleneseTestBase{

	
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
	}
		
	@Test
	public void checkLoginLink(){
		AssertJUnit.assertTrue(selenium.isTextPresent("Enter User and Password"));
	}
	
	@Test
	public void submitWithoutUserAndPassword(){
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter a valid UserName and password"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter username"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter password"));
	}
	
	@Test
	public void submitWithoutPassword(){
		selenium.type("userName", "loco");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter password"));
	}
	
	@Test
	public void submitWithoutUserName(){
		selenium.type("password", "loco");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter username"));
	}
	
	@Test
	public void submitInvalidUser(){
		selenium.type("userName", "lelliga");
		selenium.type("password", "test");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertTrue(selenium.isTextPresent("Please enter a valid UserName and password"));
	}
	
	@Test
	public void submitValidUser(){
		selenium.type("userName", "lelliga");
		selenium.type("password", "lelliga");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertFalse(selenium.isTextPresent("Please enter a valid UserName and password"));
	}
}

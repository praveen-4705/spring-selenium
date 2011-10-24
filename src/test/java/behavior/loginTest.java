package behavior;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginTest extends configurationTestBase{

	@BeforeMethod		
	public void setUp(){
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
		selenium.type("userName", "lellisga");
		selenium.type("password", "lellisga");
		selenium.submit("id=userLogin");
		selenium.waitForPageToLoad("1000");
		AssertJUnit.assertFalse(selenium.isTextPresent("Please enter a valid UserName and password"));
		selenium.open("/simple-spring-page");
		selenium.waitForPageToLoad("1000");
		selenium.click("link=LogOut");
		selenium.waitForPageToLoad("1000");
	}
}

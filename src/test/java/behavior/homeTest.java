package behavior;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class homeTest extends configurationTestBase{
		
	@BeforeMethod		
	public void setUp(){			
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

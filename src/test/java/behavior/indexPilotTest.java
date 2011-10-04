package behavior;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestCase;

public class indexPilotTest extends SeleneseTestCase{
	
	@BeforeMethod	
	public void setUp() throws Exception{	
		setUp("http://www.google.com/", "*firefox");
	}
	
	@Test
	public void testNew() throws Exception{
		
		selenium.open("/");
		selenium.type("q", "selenium rc");
		selenium.click("btnG");		
		boolean present = true;
		present = selenium.isTextPresent("google");
		AssertJUnit.assertTrue(present);
	}

}

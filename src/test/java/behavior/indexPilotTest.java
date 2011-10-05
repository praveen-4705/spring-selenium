package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;

public class indexPilotTest extends SeleneseTestBase{
	
	private SeleniumServer server;
	
//	@BeforeClass
	public void startServer() throws Exception{
		server = new SeleniumServer();
		server.start();
	}
	
//	@AfterClass
	public void stopServer(){
		server.stop();
	}
	
//	@AfterMethod
	public void stopSeleniumInstance(){
		selenium.stop();
	}
		
//	@BeforeMethod	
	public void setUp() throws Exception{	
		setUp("http://www.google.com/", "*firefox");
	}
	
//	@Test
	public void testNew() throws Exception{		
		selenium.open("/");
		selenium.type("q", "selenium rc");
		selenium.click("btnG");
		AssertJUnit.assertTrue(selenium.isTextPresent("Google"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Búsqueda avanzada"));
	}

}

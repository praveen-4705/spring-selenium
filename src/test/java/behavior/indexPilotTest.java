package behavior;

import org.testng.AssertJUnit;

public class indexPilotTest extends configurationTestBase{
	
//	private SeleniumServer server;
	
//	@BeforeClass
//	public void startServer() throws Exception{
//		server = new SeleniumServer();
//		server.start();
//	}
	
//	@AfterClass
//	public void stopServer(){
//		server.stop();
//	}
//	
//	@AfterMethod
//	public void stopSeleniumInstance(){
//		selenium.stop();
//	}
//		
////	@BeforeMethod	
//	public void setUp() throws Exception{	
//		setUp("http://www.google.com/", "*firefox");
//	}
	
//	@Test
	public void testNew() throws Exception{		
		selenium.open("/");
		selenium.type("q", "selenium rc");
		selenium.click("btnG");
		AssertJUnit.assertTrue(selenium.isTextPresent("Google"));
		AssertJUnit.assertTrue(selenium.isTextPresent("Búsqueda avanzada"));
	}

}

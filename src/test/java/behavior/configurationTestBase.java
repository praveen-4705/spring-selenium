package behavior;

import org.apache.log4j.Logger;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;

public class configurationTestBase extends SeleneseTestBase{
	
	private SeleniumServer server;
	private static Logger logger = Logger.getLogger(configurationTestBase.class);
	protected static Selenium selenium;
	
	@BeforeSuite(alwaysRun = true)
	public void startServer() throws Exception{
		server = new SeleniumServer();
		server.start();
		int port = 4444;
		String browserString = "*firefox";
		String url = "http://localhost:8080";
		
//		setUp("http://localhost:8080", "*firefox");
		
		selenium = new DefaultSelenium("localhost", port, browserString, url);
	    selenium.start();
	}
	
	@AfterSuite(alwaysRun = true)
	public void stopServer() throws Exception{
		selenium.stop();
		server.stop();
	}			
}

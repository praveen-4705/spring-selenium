package behavior;

import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;

public class configurationTestBase extends SeleneseTestBase{
	
	private SeleniumServer server;
	protected static Selenium selenium;
	
	@BeforeSuite(alwaysRun = true)
//	@Parameters({"selenium.browser","selenium.host"})
	public void startServer(/*String browser, String host*/) throws Exception{
		server = new SeleniumServer();
		server.start();
		int port = 4444;
		String host = "localhost";
		String browser = "*firefox";
		String url = "http://"+host+":8080";
		selenium = new DefaultSelenium(host, port, browser, url);
	    selenium.start();
	}
	
	@AfterSuite(alwaysRun = true)
	public void stopServer() throws Exception{
		selenium.stop();
		server.stop();
	}			
}

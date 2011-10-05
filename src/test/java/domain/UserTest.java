package domain;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserTest{
	
	User user;
		
	@BeforeMethod	
	protected void setUp() throws Exception {
        user = new User();
    }

	
	@Test
	public void testSetUserName(){
		String username = "loco";		
		AssertJUnit.assertEquals(user.getUserName(),null);
		user.setUserName(username);
		AssertJUnit.assertEquals(username, user.getUserName());		
	}
	
	@Test
	public void testSetpassword(){
		String password = "test";
		AssertJUnit.assertEquals(user.getPassword(),null);
		user.setPassword(password);
		AssertJUnit.assertEquals(password, user.getPassword());		
	}
	
	@Test
	public void testToString(){
		String username = "loco";
		user.setUserName(username);
		AssertJUnit.assertEquals("UserName: "+ username, user.toString());
	}
}

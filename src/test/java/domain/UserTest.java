package domain;

import junit.framework.TestCase;

public class UserTest extends TestCase{
	
	User user;
	
    protected void setUp() throws Exception {
        user = new User();
    }

	
	public void testSetUserName(){
		String username = "loco";
		assertNull(user.getUserName());
		user.setUserName(username);
		assertEquals(username, user.getUserName());		
	}
	
	public void testSetpassword(){
		String password = "test";
		assertNull(user.getPassword());
		user.setPassword(password);
		assertEquals(password, user.getPassword());		
	}
	
	public void testToString(){
		String username = "loco";
		user.setUserName(username);
		assertEquals("UserName: "+ username, user.toString());
	}
}

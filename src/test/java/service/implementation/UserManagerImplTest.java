package service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import repository.UserDao;
import repository.inMemory.inMemoryUserDao;
import domain.User;

public class UserManagerImplTest{
    
	private UserManagerImpl userManager;
	
	@BeforeMethod
	protected void setUp() throws Exception {
		userManager = new UserManagerImpl();
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setId(1);
		user1.setUserName("user1");
		user1.setPassword("pwd1");
		users.add(user1);
		
		User user2 = new User();
		user2.setId(2);
		user2.setUserName("user2");
		user2.setPassword("pwd2");
		users.add(user2);
		
		User user3 = new User();
		user3.setId(3);
		user3.setUserName("user3");
		user3.setPassword("pwd3");
		users.add(user3);
		
		UserDao userDao = new inMemoryUserDao(users);
		userManager.setUserDao(userDao);
    }
	
	@Test
	public void testGetUsers(){
    	List<User> users = userManager.getUsers();
    	AssertJUnit.assertEquals("It's not getting all users",users.size(), 3);
    }
    
    @Test
	public void testIsValidUser(){
    	User first = new User();
    	first.setUserName("testUser");
    	first.setPassword("testPwd");
    	boolean fuser = userManager.isValidUser(first);
    	AssertJUnit.assertFalse("User should not be a valid one", fuser);
    	
    	User validOne = new User();
    	validOne.setUserName("user2");
    	validOne.setPassword("pwd2");
    	boolean vuser = userManager.isValidUser(validOne);
    	AssertJUnit.assertTrue("This user should be a valid one",vuser);
    }
    
    @Test
	public void testGetById(){
    	User invalidUser = new User();
    	invalidUser.setId(4);
    	AssertJUnit.assertNull("No user, this should be null",userManager.getById(invalidUser.getId()));
    	User validUser = userManager.getUsers().get(0);
    	User dbUser = userManager.getById(validUser.getId());
    	AssertJUnit.assertEquals("Users should be equeals", validUser.getId(), dbUser.getId());
    }
}

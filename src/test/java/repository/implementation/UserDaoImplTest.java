package repository.implementation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import repository.UserDao;
import domain.User;

@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class UserDaoImplTest extends AbstractTransactionalTestNGSpringContextTests{

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
        
    
    @BeforeMethod
    protected void onSetUpInTransaction() throws Exception {    	
        super.deleteFromTables(new String[] {"users"});
        super.executeSqlScript("file:src/main/db/load_users.sql", true);
    }
    
    @Test
	public void testGetUsers(){
    	List<User> users = userDao.getUsers();
    	AssertJUnit.assertNotNull("Users list should not be null",users);
    	AssertJUnit.assertEquals("Size should be 3", users.size(),3);
    }
    
    @Test
	public void testIsValid(){
    	User invalid = new User();
    	invalid.setUserName("invalid");
    	invalid.setPassword("invalidPwd");
    	boolean invalidUser = userDao.isValidUser(invalid);
    	AssertJUnit.assertFalse("This user should not be valid",invalidUser);    	
    	User valid = new User();
    	valid.setUserName("lellisga");
    	valid.setPassword("lellisga");
    	boolean validUser = userDao.isValidUser(valid);
    	AssertJUnit.assertTrue("This should be a valid user", validUser);
    }
    
    @Test
    @Rollback(true)
	public void testCreate(){
    	List<User> oldUsers = userDao.getUsers();
    	AssertJUnit.assertEquals("It should only have 3 users",oldUsers.size(), 3);
    	User user = new User();
    	user.setUserName("test1");
    	user.setPassword("test1");
    	userDao.create(user);
    	List<User> newUsers = userDao.getUsers();
    	AssertJUnit.assertEquals("It should have 4 users",oldUsers.size() + 1 , newUsers.size());
    }
    
    @Test
	public void testGetById(){
    	User invalidUser = new User();
    	invalidUser.setId(4);
    	AssertJUnit.assertNull("No user, this should be null",userDao.getById(invalidUser.getId()));
    	User validUser = userDao.getUsers().get(0);
    	User dbUser = userDao.getById(validUser.getId());
    	AssertJUnit.assertEquals("Users should be equeals", validUser.getId(), dbUser.getId());
    }        
}
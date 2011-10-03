package repository.implementation;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import repository.UserDao;
import domain.User;

public class UserDaoImplTest extends AbstractTransactionalDataSourceSpringContextTests{

    protected final Log logger = LogFactory.getLog(getClass());
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
    @Override
    protected String[] getConfigLocations() {
        return new String[] {"test-context.xml"};
    }
    
    @Override
    protected void onSetUpInTransaction() throws Exception {    	
        super.deleteFromTables(new String[] {"users"});
        super.executeSqlScript("file:src/main/db/load_users.sql", true);
    }
    
    public void testGetUsers(){
    	List<User> users = userDao.getUsers();
    	assertNotNull("Users list should not be null",users);
    	assertEquals("Size should be 3", users.size(),3);
    }
    
    public void testIsValid(){
    	User invalid = new User();
    	invalid.setUserName("invalid");
    	invalid.setPassword("invalidPwd");
    	boolean invalidUser = userDao.isValidUser(invalid);
    	assertFalse("This user should not be valid",invalidUser);
    	
    	User valid = new User();
    	valid.setUserName("lellisga");
    	valid.setPassword("lellisga");
    	boolean validUser = userDao.isValidUser(valid);
    	assertTrue("This should be a valid user", validUser);
    }
    
    public void testCreate(){
    	List<User> oldUsers = userDao.getUsers();
    	assertEquals("It should only have 3 users",oldUsers.size(), 3);
    	User user = new User();
    	user.setUserName("test1");
    	user.setPassword("test1");
    	userDao.create(user);
    	List<User> newUsers = userDao.getUsers();
    	assertEquals("It should have 4 users",oldUsers.size() + 1 , newUsers.size());
    }
    
    public void testGetById(){
    	User invalidUser = new User();
    	invalidUser.setId(4);
    	assertNull("No user, this should be null",userDao.getById(invalidUser.getId()));
    	User validUser = userDao.getUsers().get(0);
    	User dbUser = userDao.getById(validUser.getId());
    	assertEquals("Users should be equeals", validUser.getId(), dbUser.getId());
    }
        
}

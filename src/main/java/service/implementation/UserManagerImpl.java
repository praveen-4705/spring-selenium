package service.implementation;

import java.util.List;

import repository.UserDao;
import service.UserManager;
import domain.User;

public class UserManagerImpl implements UserManager{
	
	private UserDao userDao;	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public boolean isValidUser(User user) {
		return userDao.isValidUser(user);
	}

}

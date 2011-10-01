package service.implementation;

import java.util.List;

import domain.User;
import repository.UserDao;
import service.UserManager;

public class UserManagerImpl implements UserManager{
	
	private UserDao userdao;
	
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	
	public List<User> getUsers() {
		return userdao.getUsers();
	}

	public boolean isValidUser(User user) {
		return userdao.isValidUser(user);
	}

}

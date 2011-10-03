package service;

import java.util.List;

import domain.User;

public interface UserManager {
	
	public List<User> getUsers();
	public boolean isValidUser(User user);
	public User getById(long userId);
}

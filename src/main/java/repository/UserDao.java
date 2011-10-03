package repository;

import java.util.List;

import domain.User;

public interface UserDao {

	public List<User> getUsers();
	
	public boolean isValidUser(User user);
	
	public void create(User user);
	
	public User getById(long userId);
}

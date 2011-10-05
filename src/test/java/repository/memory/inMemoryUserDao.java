package repository.memory;

import java.util.List;

import repository.UserDao;
import domain.User;

public class inMemoryUserDao implements UserDao{
	
	private List<User> users;
	
	public inMemoryUserDao(List<User> users){
		this.users = users;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public boolean isValidUser(User user) {
		boolean response = false;
		for(User u : users){
			if(u.getUserName().equals(user.getUserName()) && u.getPassword().equals(user.getPassword())){
				response = true;
				break;
			}
		}
		return response;
	}

	public void create(User user) {
		users.add(user);
	}

	public User getById(long userId) {
		User user = null;
		for(User u : users){
			if(u.getId() == userId){
				user = u;
				break;
			}
		}
		
		return user;
	}
}

package repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import domain.Product;
import domain.User;
import repository.UserDao;

public class UserDaoImpl extends SimpleJdbcDaoSupport implements UserDao{

    protected final Log logger = LogFactory.getLog(getClass());

	public List<User> getUsers() {
		logger.info("Getting users!");
		List<User> users = getSimpleJdbcTemplate().query("select id, username, password from users", new UserMapper());
		logger.info("Returned " + users.size() + " users");
		return users;
	}

	public boolean isValidUser(User user) {
		boolean response = false;
		
		List<User> dbuser = getSimpleJdbcTemplate().query("select id, username, password from users where username = :username and password = :password",new UserMapper(),new MapSqlParameterSource()
		.addValue("username",user.getUserName())
		.addValue("password", user.getPassword()));
		
		if(dbuser != null && !dbuser.isEmpty())
			response = true;
		
		return response;
	}

	public void create(User user) {
		logger.info("Creating User: " + user.toString());
		int count = getSimpleJdbcTemplate().update("INSERT INTO users (username, password) values (:username, :password)", new MapSqlParameterSource()
		.addValue("username", user.getUserName())
		.addValue("password", user.getPassword()));
		logger.info(count + " users have been inserted");
	}
	private static class UserMapper implements ParameterizedRowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			 User user = new User();
			 user.setId(rs.getInt("id"));
			 user.setUserName(rs.getString("username"));
			 user.setPassword(rs.getString("password"));
			 return user;
		}
		
	}
	
}


package domain;

import java.io.Serializable;

public class User implements Serializable{
	
	private String userName;
	private String password;
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("UserName: ");
		buffer.append(userName);
		return buffer.toString();
	}
}

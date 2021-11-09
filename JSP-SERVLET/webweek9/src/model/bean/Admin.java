package model.bean;

public class Admin {
	String username;
	String password;
	String id;
	int key;
	
	public Admin(String username, String password,String id,int key) {
		super();
		this.username = username;
		this.password = password;
		this.key=key;
		this.id=id;
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + "]";
	}
	
	
}

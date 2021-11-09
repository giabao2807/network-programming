package model.bean;

public class People {
	String id;
	String name;
	String hobbies;
	String gender;
	
	
	public People(String id, String name, String hobbies, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.hobbies = hobbies;
		this.gender = gender;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHobbies() {
		return hobbies;
	}


	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}


	public String isGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", hobbies=" + hobbies + ", gender=" + gender + "]";
	}
}

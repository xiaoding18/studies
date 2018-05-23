package study_serialize;

import java.io.Serializable;

public class Students implements Serializable{

	private static final long serialVersionUID = 165461654646876163L;
	public transient String username;
	@Override
	public String toString() {
		return "Students [username=" + username + ", age=" + age + "]";
	}
	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Students(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}
	private int age;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

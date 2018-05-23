package study_BeanUtils;

public class User {

	private String username;
	private String password;
	private int age;
	private String school;
	/**
	 * @param username
	 * @param password
	 * @param age
	 * @param school
	 */
	public User(String username, String password, int age, String school) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.school = school;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", age=" + age + ", school=" + school + "]";
	}
	/**
	 * 
	 */
	public User() {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
}

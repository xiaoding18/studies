package study_Generic;

public  class Student {

	private String name;
	private String password;
	private int age;
	private String realName;
	public String getName() {
		return name;
	}

	public Student(String name, String password, int age, String realName) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.realName = realName;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public void work(){
	}
	public void stydy(){
		
	}
	
}

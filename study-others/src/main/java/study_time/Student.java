package study_time;

public class Student {

	private String username;
	private String password;
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
	public Student(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj instanceof Student) {
            Student stu = (Student)obj;
            if(stu.getUsername().equals(this.username)){
            	return true;
            }
        }
        return false;
	}
	
}

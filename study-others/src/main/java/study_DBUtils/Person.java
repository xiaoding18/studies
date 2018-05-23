package study_DBUtils;

public class Person {

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + ", classroom=" + classroom
				+ "]";
	}
	private String id;
	private String name;
	private String age;
	private String score;
	private String classroom;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public Person(String id, String name, String age, String score, String classroom) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
		this.classroom = classroom;
	}
	public Person() {
	}
	
}

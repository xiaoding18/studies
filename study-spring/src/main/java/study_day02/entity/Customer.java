package study_day02.entity;

public class Customer {
	
	private int id;
	private String name;
	private String sex;
	public Customer(int id, String name, String sex) {
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	public Customer() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	
	
	
}

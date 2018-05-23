package study_reflet;

public class Person {

	//创建一个Person类用于描述人
	public String username;
	public int age;
	public String time;
	public Person() {
	}
	public Person(String username, int age) {
		this.username = username;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [username=" + username + ", age=" + age + ", time=" + time + "]";
	}
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
	//创建一个Person的特有方法
	public void eat() {
		System.out.println(username+"在吃饭,"+"用时"+time+"分钟");
	}
	
	private void privateEat() {
		System.out.println(username+"在吃饭");
	}
	
	public void Play(String time) {
		System.out.println(username+"玩游戏"+time);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	//特有的吃饭
	public void Eat(String s1,String s2) {
		System.out.println(s1+"吃饭吃了"+s2+"小时");
	}
	
	

}

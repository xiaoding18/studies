package study_Generic;

public class Tongxue extends Student {

	public Tongxue(String name, String password, int age, String realName) {
		super(name, password, age, realName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"(学习委员)正在工作");
	}

	@Override
	public void stydy() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"(学习委员)正在看书");
	}

}

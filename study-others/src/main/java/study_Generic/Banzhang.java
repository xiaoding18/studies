package study_Generic;

public class Banzhang extends Student {

	
	public Banzhang(String name, String password, int age, String realName) {
		super(name, password, age, realName);
	}

	@Override
	public void work() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"(班长)正在工作");
	}

	@Override
	public void stydy() {
		// TODO Auto-generated method stub
		System.out.println(getName()+"(班长)正在看书");
	}

}

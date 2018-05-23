package study_day04.entity;

/**   
* @Description 
* @author xiaoding
* @date 2018年3月24日 上午9:55:51   
*/
public class Account {

	private Integer id;
	private String name;
	private Double money;
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", money=" + money + "]";
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(Integer id, String name, Double money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
}
	
	

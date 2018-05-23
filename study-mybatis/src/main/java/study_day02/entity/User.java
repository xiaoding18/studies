package study_day02.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**   
* @Description 用户的实体类
* @author xiaoding
* @date 2018年4月2日 下午2:23:29   
*/
public class User implements Serializable {

	
	private Integer id;
	private String username;
	private String sex;
	private String address;
	private Date birthday;
	
	private List<Order> orders;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + ", orders=" + orders + "]";
	}

	public User(Integer id, String username, String sex, String address, Date birthday, List<Order> orders) {
		super();
		this.id = id;
		this.username = username;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public User() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}

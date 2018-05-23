package study_day01.entity;

import java.io.Serializable;
import java.sql.Date;

/**   
* @Description 用户的实体类
* @author xiaoding
* @date 2018年4月2日 下午2:23:29   
*/
public class User extends study_day02.entity.User implements Serializable{

	
	private Integer id;
	private String username;
	private String sex;
	private String address;
	private Date birthday;
	public User(Integer id, String username, String sex, String address, Date birthday) {
		super();
		this.id = id;
		this.username = username;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
	}
	public User() {
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + "]";
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

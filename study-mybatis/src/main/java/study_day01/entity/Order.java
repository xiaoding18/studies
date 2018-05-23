package study_day01.entity;

import java.util.Date;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月3日 上午10:32:58   
*/
public class Order {

	private String id;
	private String userId;
	private int number;
	private Date createTime;
	private String note;
	public Order(String id, String userId, int number, Date createTime, String note) {
		super();
		this.id = id;
		this.userId = userId;
		this.number = number;
		this.createTime = createTime;
		this.note = note;
	}
	public Order() {
		super();
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", number=" + number + ", createTime=" + createTime
				+ ", note=" + note + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}

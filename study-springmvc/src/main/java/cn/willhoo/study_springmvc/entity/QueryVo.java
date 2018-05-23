package cn.willhoo.study_springmvc.entity;

import java.util.List;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月6日 下午6:32:57   
*/
public class QueryVo {

	private List<User> userList;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
}

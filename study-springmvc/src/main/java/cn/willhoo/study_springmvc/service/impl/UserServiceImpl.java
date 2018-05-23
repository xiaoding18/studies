package cn.willhoo.study_springmvc.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.willhoo.study_springmvc.entity.User;
import cn.willhoo.study_springmvc.mapper.UserMapper;
import cn.willhoo.study_springmvc.service.UserService;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月5日 下午4:20:46   
*/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public List<User> findAllUser() {
		return mapper.findAllUser();
	}

	@Override
	public User findUserById(int id) {
		return mapper.findUserById(id);
	}

	@Override
	public List<User> findUserListByAddress(String address) {
		return mapper.findUserListByAddress(address);
	}

	@Override
	public void saveFile(String fileName) {
		mapper.saveFile(fileName);
	}

	@Override
	public void deleteAll(Long[] ids) {
		
	}
	
	

}

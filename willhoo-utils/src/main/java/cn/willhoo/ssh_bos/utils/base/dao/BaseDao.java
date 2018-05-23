package cn.willhoo.ssh_bos.utils.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月10日 下午9:48:41   
* 使用NoRepositoryBean让这个接口不被SpringJPA管理
*/
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Long>,JpaSpecificationExecutor<T>{

}

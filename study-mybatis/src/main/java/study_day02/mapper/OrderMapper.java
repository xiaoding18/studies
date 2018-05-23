package study_day02.mapper;

import java.util.List;

import study_day02.entity.Order;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月3日 上午10:32:24   
*/
public interface OrderMapper {

	/**
	 * 获取所有的订单
	 */
	List<Order> findAllOrders(); 
	
	/**
	 * 还是获取所有的订单,但是这次使用resultMap实现
	 */
	List<Order> findAllOrdersByResultMap();
	
	List<Order> findAllOrderByDynamic();
	
	/**
	 * 获取所有的订单对象,并且获取到得到这个订单的用户
	 */
	List<Order> oneToOneSelect();
}

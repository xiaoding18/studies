package study_jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**   
* @Description 测试jedis的基本使用
* @author xiaoding
* @date 2018年4月20日 上午10:03:44   
*/
public class TestJedis {

	
	/** 
	 * 将数据存入redis
	 */
	@Test
	public void Test_1() {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.set("email", "123@qq.com");
	}
	
	
	/**
	 * 取出数据
	 */
	@Test
	public void Test_2() {
		
	}
}

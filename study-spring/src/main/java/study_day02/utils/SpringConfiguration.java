package study_day02.utils;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**   
* @Description spring的配置类
* @author xiaoding
* @date 2018年3月21日 上午10:49:53   
*/
@Configuration//这个注解表示这个类是spring的配置类
@ComponentScan("study_day02")//表示spring注解扫描需要扫描的包
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {

	@Value("${db.driverClass}")
	private String driverClass;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	
	@Value("${username}")
	private String testUser;
	
	
	
	//以上只能代替掉spring的部分信息,其实spring注解也是能创建QueryRunner对象和DataSource对象的
	@Bean
	public ComboPooledDataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(url);
		dataSource.setDriverClass(driverClass);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	//当有多个数据源对象时,可以使用Qualifier来指定需要使用的dataSource数据源
	/**
	 * 在定义了Bean的数据源上,并且有多个参数类型的bean时,
	 * 我们可以通过Qualifierd来为其参数唯一赋值(那么意思就是说默认注入的方式是Autowired咯?)
	 * @param dataSource
	 * @return
	 */
	@Bean
	public QueryRunner getQueryRunner(DataSource dataSource) {
		return new QueryRunner(dataSource);
	}
	
	
	
	
	
	@Bean(name="myList2")
	public List<String> setList_2(){
		return new LinkedList<>();
	}
	@Bean(name="myList1")
	public List<String> setList_1(){
		return new ArrayList<>();
	}
	
}

package cn.willhoo.study_springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**   
* @Description 自定义参数类型转换器
* 可以被应用于以下类似的需求,当页面需要插入一个时间,但是实体类中的时间类型是Date类型而不是String类型时,这时候使用自带的转换器不能满足要求
* 这时我们可以定义一个参数类型转换器
* 在编写完这个类之后,我们还需要在配置文件中声明这个转换器
* 
* 问题来了,这个转换器到底是什么时候被调用的呢??在一个什么时机被调用呢?
*/
public class SimpleDateConverter implements Converter<String,Date> {

	/**
	 * 当定义一个自定义转换器的时候,需要自行编写这个方法
	 */
	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//当转换出现异常时,返回null
		return null;
	}
}

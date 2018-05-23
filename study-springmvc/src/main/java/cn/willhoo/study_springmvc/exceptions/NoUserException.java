package cn.willhoo.study_springmvc.exceptions;

/**   
* @Description 定义一个自定义异常,当没有用户时将会抛出这个异常
* @author xiaoding
* @date 2018年4月12日 下午8:45:02   
*/
public class NoUserException extends Exception{

	private static final long serialVersionUID = 6194094629307313883L;
	
	public NoUserException(String message) {
		super(message);
	}
	
	public NoUserException() {};

}

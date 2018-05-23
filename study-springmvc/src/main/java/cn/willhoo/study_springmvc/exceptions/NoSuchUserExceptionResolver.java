package cn.willhoo.study_springmvc.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月12日 下午8:46:57   
*/
@Component
public class NoSuchUserExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		NoUserException userEx = null;
		System.err.println(handler.getClass());
		//如果是没有合适的用户引发的异常
		if(ex instanceof NoUserException) {
			userEx = (NoUserException) ex;
		}else {
			userEx = new NoUserException("未知的异常!");
		}
		
		//将用户导向至显示错误的页面
		ModelAndView mav = new ModelAndView();
		//输出我们的异常信息
		mav.addObject("exceptionMessage",userEx.getMessage());
		mav.setViewName("error/error");
		
		return mav;
	}

}

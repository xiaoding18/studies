package cn.willhoo.ssh_bos.utils.base;

import org.springframework.data.domain.PageImpl;

import java.util.List;
/**   
* @Description 用来分页的对象
* @author xiaoding
* @date 2018年4月19日 下午2:36:09   
*/

public class Pageable<T> extends PageImpl<T>{

	private static final long serialVersionUID = -4753717281207517682L;

	public Pageable(List<T> content) {
		super(content);
	}

	public Pageable(List<T> content, org.springframework.data.domain.Pageable pageable, long total) {
		super(content,pageable,total);
	}


	
}


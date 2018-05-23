package cn.willhoo.study_lucene_solr.mapper;

import java.util.List;

import cn.willhoo.study_lucene_solr.domain.Book;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月8日 下午9:06:37   `
*/
public interface BookMapper {

	List<Book> findAll();
}
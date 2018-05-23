package cn.willhoo.study_lucene_solr.mapper;

import java.util.List;

import cn.willhoo.study_lucene_solr.domain.Product;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月8日 下午9:06:37   
*/
public interface ProductMapper {

	List<Product> findAll();
}

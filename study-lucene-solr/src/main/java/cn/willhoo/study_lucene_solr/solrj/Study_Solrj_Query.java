package cn.willhoo.study_lucene_solr.solrj;

import cn.willhoo.ssh_bos.utils.constant.SolrAddress;
import cn.willhoo.study_lucene_solr.mapper.BookMapper;
import cn.willhoo.study_lucene_solr.mapper.ProductMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
* @Description
* @author xiaoding
* @date 2018年5月17日 上午10:11:03
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Study_Solrj_Query {

	private HttpSolrServer server = new HttpSolrServer(SolrAddress.SOLR_ADDRESS);

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private ProductMapper productMapper;


	@After
	public void after() throws SolrServerException, IOException {
		System.err.println("已经提交");
		server.commit();
	}


	@Test
	public void Test_1() throws SolrServerException {
		SolrQuery query = new SolrQuery();
		
		//设置需要查询的语句
		query.setQuery("product_name:花儿");

		//在这里设置过滤的条件
		query.setFilterQueries("product_price:{10 TO 20}");

		//添加一个排序的条件
		query.addSort("product_price",ORDER.asc);
		//设置分页
		query.setStart(0);//设置起始索引号
		query.setRows(2);//设置每页的大小

		//设置需要显示的域,也就是设置之后没有在这里的域将不会返回
		query.setFields("id,product_name,product_price,product_catalog,product_catalog_name");

		//设置返回的值类型为json
		query.set("wt", "json");


		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");//设置需要高亮的域
		query.setHighlightSimplePre("<font color='red'>");//指定高亮关键字前面的标签
		query.setHighlightSimplePost("</font>");//设置后面的标签


		/*//设置分组
		query.setFacet(true);//需要分组
		query.addFacetQuery("product_name:花儿");
		query.addFacetField("");//设置需要进行分组的域
		*/
		//执行查询
		QueryResponse resp = server.query(query);
		
		//使用注解的方式来获取bean
		//List<Book> books = resp.getBeans(Book.class);
		
		//使用普通的方式获取到结果集
		SolrDocumentList results = resp.getResults();

		//输出总记录数
		System.err.println(results.getNumFound());

		//得到已经高亮的内容
		Map<String, Map<String, List<String>>> highlighting = resp.getHighlighting();
		
		for (SolrDocument solrDocument : results) {
			String id = solrDocument.get("id").toString();

			System.out.println("id为:"+solrDocument.get("id"));

			System.out.println("高亮的属性名称为:"+highlighting.get(id).get("product_name").get(0));
			System.out.println("没有高亮商品名称为:"+solrDocument.get("product_name"));
			System.out.println("商品价格为:"+solrDocument.get("product_price"));
			System.out.println("商品图片地址为:"+solrDocument.get("product_picture"));
			System.out.println("商品分类为:"+solrDocument.get("product_catalog_name"));
//			System.out.println("商品描述为:"+solrDocument.get("product_description"));
//			System.out.println("商品分类名称为:"+solrDocument.get("id"));
		}

	}








}

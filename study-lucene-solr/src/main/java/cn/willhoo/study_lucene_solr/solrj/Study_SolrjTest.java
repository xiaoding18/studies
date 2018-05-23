package cn.willhoo.study_lucene_solr.solrj;

import cn.willhoo.ssh_bos.utils.constant.SolrAddress;
import cn.willhoo.study_lucene_solr.domain.Book;
import cn.willhoo.study_lucene_solr.domain.Product;
import cn.willhoo.study_lucene_solr.mapper.BookMapper;
import cn.willhoo.study_lucene_solr.mapper.ProductMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**   
* @Description 我们可以通过编写java程序来实时更改solr服务器的状态
* @author xiaoding
* @date 2018年5月15日 下午7:11:44   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Study_SolrjTest {

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
	
	/**
	 * 添加个索引
	 * 添加索引必须要有id,如果没有id就会报错
	 * 这个是使用solr所默认的方式来创建这个对象
	 * 我们其实还有一种直接使用javaBean的方式来获取这个对象
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void Test_1() throws SolrServerException, IOException {
		
		//得到一个solr所特有的文档对象
		SolrInputDocument doc = new SolrInputDocument();
		//还是添加一个域,这个域是string的key,object的value
		doc.addField("id", "1234");
		doc.addField("name", "肖鼎");
		
		//提交,这个好像还是有事务的
		server.add(doc);
	}
	
	
	/**
	 * 删除一个索引(那么意思是说也可以使用条件删除索引咯?)
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void Test_2() throws SolrServerException, IOException {
		
		//根据ID删除一个索引(相当于数据库中的根据主键)
		//server.deleteById("1234");

		//根据条件删除一个索引,这个条件是一个string的查询字符串,将会把所有符合条件的记录删除
		server.setBaseURL("http://xiaoding-pc:7080/solr/collection2");
		server.deleteByQuery("*:*");
	}
	
	/**
	 * 测试查询,这里的查询需要查询表达式
	 * @throws Exception
	 */
	@Test
	public void Test_3() throws Exception {
		//需要先创建一个用于查询的对象query
		SolrQuery query = new SolrQuery("*:*");
		
		//这个是得到查询到的结果?
		QueryResponse repo = server.query(query);
		
		//使用注解的方式自动获取结果集
		List<Book> beans = repo.getBeans(Book.class);
		
		beans.forEach(System.err::println);
		
		/*//不使用注解的方式遍历结果集
		for (SolrDocument doc : results) {
			System.err.println(doc.get("id"));
			System.err.println(doc.get("bookname"));
		}*/
		
	}
	
	/**
	 * 从数据库读取实际的数据,然后写入到solr服务器中,如果直接用addBeans会发生什么结果?
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void Test_4() throws SolrServerException, IOException {
		List<Book> books = bookMapper.findAll();
		
//		books.forEach(System.err::println);
		server.addBeans(books);
	}
	
	/**
	 * 由于solr默认是使用collection1进行插入,我们现在要使用collection2来进行插入
	 * 	(其实collection2没有bookname这个域,所以我们还是用collection1来插入吧)
	 * @throws IOException 
	 * @throws SolrServerException 
	 */
	@Test
	public void Test_5() throws SolrServerException, IOException {
		List<Book> list = bookMapper.findAll();
		server.setBaseURL(SolrAddress.SOLR_ADDRESS+"collection1");
		list.forEach(System.err::println);
		server.addBeans(list);
	}
	
	@Test
	public void Test_6() throws SolrServerException, IOException {
		List<Product> list = productMapper.findAll();
		System.err.println(list.get(0));
//		list.forEach(System.out::println);
		server.addBeans(list);
	}
	
	
}

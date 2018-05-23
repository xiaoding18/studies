package cn.willhoo.study_lucene_solr.studies;

import cn.willhoo.study_lucene_solr.domain.Book;
import cn.willhoo.study_lucene_solr.mapper.BookMapper;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月14日 下午3:55:28   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Studies {

	
	
	private List<Document> docs;
	
	private IndexWriter iw;
	private IndexSearcher searcher;
	private QueryParser parser;
	
	@Autowired
	private BookMapper mapper;


	//初始化
	@Autowired
	public void before() throws Exception {
		File file = new File("d:/indexes");
		Directory dir = FSDirectory.open(file);
		List<Book> books = mapper.findAll();
		docs = books.stream()
			.map(this::BookToDocument)
			.collect(Collectors.toList());
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, new IKAnalyzer());
		//初始化写入对象
		iw = new IndexWriter(dir,iwc);
		if(new File("d:/indexes").list().length==0) {
			return ;
		}
		DirectoryReader reader = DirectoryReader.open(dir);
		//初始化搜索对象
		searcher = new IndexSearcher(reader);
		parser = new QueryParser("",new IKAnalyzer());
		
	}
	
	//索引数据到本地文件系统中
	@Test
	public void Test_1() throws Throwable {
		//添加所有的document到本地文件中
		for (Document doc : docs) {
			iw.addDocument(doc);
		}
		
	}
	
	@Test
	public void Test_2() throws Exception {
		Query query = parser.parse("bookName:java");
		TopDocs topDocs = searcher.search(query, 10);
		ScoreDoc[] results = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : results) {
			Document doc = searcher.doc(scoreDoc.doc);
			Book book = DocumentToBook(doc);
			System.err.println(book);
		}
	}
	
	@After
	public void after() throws IOException {
		if(iw != null) {
			iw.close();
		}
		
		
		
	}
	
	public Book DocumentToBook(Document doc) {
		Book book = new Book();
		book.setId(doc.get("bookId"));
		book.setBookname(doc.get("bookName"));
		book.setPic(doc.get("bookPic"));
		book.setPrice(Float.parseFloat(doc.get("bookPrice")));
		book.setBookdesc(doc.get("bookDesc"));
		return book;
	}
	
	public Document BookToDocument(Book book) {
		Document document = new Document();
		
		//分别将book对象的属性存放到域中
		/*
		 *为了系统性能的提升,以及系统存储量的减少,我们需要使用更加精确的域,在这里我们对于使用域的需求如下
		 *bookId
		 *	不分词,索引,保存
		 *bookName
		 *	分词,索引,保存
		 *bookPic
		 *	不分词,不索引,保存
		 *bookPrice
		 *	不分词,索引,保存
		 *bookDesc
		 *  不分词,不索引,不保存
		 */
		document.add(new StringField("bookId", book.getId()+"",Store.YES));
		document.add(new TextField("bookName", book.getBookname(),Store.YES));
		document.add(new StoredField("bookPic", book.getPic()+""));
		document.add(new DoubleField("bookPrice", book.getPrice(),Store.YES));
		document.add(new TextField("bookDesc", book.getBookdesc()+"",Store.NO));
		
		return document;
	}
}

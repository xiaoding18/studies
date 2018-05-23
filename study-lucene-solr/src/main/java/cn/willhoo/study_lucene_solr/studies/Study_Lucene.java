package cn.willhoo.study_lucene_solr.studies;

import cn.willhoo.study_lucene_solr.domain.Book;
import cn.willhoo.study_lucene_solr.mapper.BookMapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月8日 下午9:06:24   
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Study_Lucene {
	
	private List<Document> docs;
	
	private Analyzer analyzer = new IKAnalyzer();

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
	
	
	/**
	 * 对一些对象建立索引的实现
	 * @throws IOException
	 */
	@Test
	public void Test_1() throws IOException {
		//先得到原始数据
		List<Book> list = mapper.findAll();
		
		//将原始数据封装成为文档对象的集合
		List<Document> documents = list.stream()
					.map(this::BookToDocument)
					.collect(Collectors.toList());
		
		//建立分词器对象
		/**
		 * 默认将会使用standardAnalyzer分词器,这个分词器对于中文的支持不是很好,我们选择使用第三方的分词器
		 * ikanalyzer分词器
		 * 	这是一个比较优秀的支持中文的分词器,并且对于扩展性有良好的支持
		 * 	这个分词器需要一个配置文件,我们通常将这个配置文件放在classpath下面
		 * 	我们将会使用两个文件,一个文件是ext.dic文件,还有一个文件是stopword.dic文件,分别是扩展词汇和停用词汇
		 */
		Analyzer analyzer = new IKAnalyzer();
		
		//建立索引库配置对象
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		//建立索引库的目录对象,将会把索引文件写入这个目录中
		Directory directory = FSDirectory.open(new File("d:/indexes"));
		
		//创建索引库的操作对象
		IndexWriter iw = new IndexWriter(directory, iwc);
		
		iw.addDocuments(documents);
		
		iw.close();
		
	}
	
	//将一个book对象封装到文档对象中,返回这个文档对象
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
	
	private static final String INDEX_PATH = "d:/indexes";
	
	
	/**
	 * 检索索引实现
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void Test_2() throws IOException, ParseException {
		//建立分析器对象,使用的分析器对象要和创建这些索引的时候的索引的分析器相同
		Analyzer analyzer = new IKAnalyzer();
		
		//建立Query查询对象
		/*
		 * 第一个参数是需要使用的域
		 * 第二个参数是需要使用的分析器
		 */
		QueryParser qp = new QueryParser("bookName",analyzer);
		
		//使用查询转换对象获取一个真正的查询对象,有点类似于将一个sql语句解析成为一个sql语句的解析对象
		Query query = qp.parse("bookName:lucene");
		
		//建立索引库的位置对象(需要知道这些索引存放在哪里
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		//将索引读取到内存中,需要将索引先读取打内存中才能进行查找,硬盘很慢
		IndexReader reader = DirectoryReader.open(directory);
		
		//进行索引的搜索,我们需要创建一个索引的搜索对象
		IndexSearcher searcher = new IndexSearcher(reader);
		
		//TopDocs表示结果集??
		/*
		 * 第一个参数表示查询对象,第二个参数表示需要几条结果,比如说查询到了10条结果,但是我们只是需要1条结果,这时我们就可以填写1,就只会获取到1条结果
		 */
		TopDocs topDocs = searcher.search(query, 2);
		
		System.out.println("总记录数量"+topDocs.totalHits);
		System.out.println("实际获取到的数量:"+topDocs.scoreDocs.length);
		
		
		//获取的所有的结果
		ScoreDoc[] result = topDocs.scoreDocs;
		ScoreDoc[] copyOfRange = Arrays.copyOfRange(result, 1, 2);
		
		for (ScoreDoc scoreDoc : copyOfRange) {
			System.out.println("----------------------");
			
			//获取文档的id
			int doc = scoreDoc.doc;
			
			//获取文档的分值
			float score = scoreDoc.score;
			
			System.out.println("当前文档的id:"+doc+"当前文档的分值:"+score);
			
			//获取真正的文档
			Document document = searcher.doc(doc);
			
			System.out.println("图书Id:"+document.get("bookId"));
			System.out.println("图书名称:"+document.get("bookName"));
			System.out.println("图书价格:"+document.get("bookPrice"));
			System.out.println("图书图片:"+document.get("bookPic"));
			System.out.println("图书描述"+document.get("bookDesc"));
		}
		
		reader.close();
		
	}
	
	
	
	
	
	/**
	 * 自己写一遍写入索引的过程
	 * @throws Throwable 
	 */
	@Test
	public void Test_3() throws Throwable {
		
		//先要获取到原始数据
		List<Book> bookList = mapper.findAll();
		
		//将原始数据封装成Document集合
		List<Document> documentList = bookList.stream()
			.map(this::BookToDocument)
			.collect(Collectors.toList());
		
		//设置需要使用的分词器IKAnalyzer
		Analyzer analyzer = new IKAnalyzer();
		
		//创建写入索引库的配置对象
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		
		//得到需要输出的索引库目录对象
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		
		
		
		//得到真正用来写入的IndexWriter对象
		IndexWriter iw = new IndexWriter(directory, iwc);
		
		//执行真正的写入
		iw.addDocuments(documentList);
		
		iw.close();
	}
	
	
	
	
	/**
	 * 在索引库中查找一条记录
	 * @throws Throwable 
	 */
	
	@Test
	public void Test_4() throws Throwable {
		
		//先得到分词器
		Analyzer analyzer = new IKAnalyzer();
		
		//将索引库中的数据读取到内存中
		Directory directory = FSDirectory.open(new File(INDEX_PATH));
		
		//获取到查询索引库的对象
		/*
		 * 第一个参数是需要conga哪个域中查询数据,第二个参数是使用哪个分词器来进行操作
		 */
		QueryParser qp = new QueryParser("bookName", analyzer);
		
		Query query = qp.parse("bookName:lucene");
		
		
		//将索引中的文件信息读取到文件中
		IndexReader ir = DirectoryReader.open(directory);
		
		//建立一个对象用来查询
		IndexSearcher is = new IndexSearcher(ir);
		
		//根据语句查询两条记录
		TopDocs results = is.search(query, 2);
		
		//输出本次查询的结果
		//总记录数量
		int totalHits = results.totalHits;
		//最大分值
		float maxScore = results.getMaxScore();
		
		//默认将会按照分值之间的降序进行排列
		for(ScoreDoc scoreDoc:results.scoreDocs) {
			
			//得到id
			int docId = scoreDoc.doc;
			
			//得到分值
			float score = scoreDoc.score;
			
			//输出搜索的结果
			Document document = is.doc(docId);
			
			document.forEach(System.out::println);
		}
		
	}
	
	/**
	 * 根据条件删除一个索引
	 * @throws IOException 
	 */
	@Test
	public void Test_5() throws IOException {
		Analyzer analyzer = new IKAnalyzer();
		
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		
		//获取到写入索引的对象
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		
		IndexWriter iw = new IndexWriter(dic, iwc);
		
		//得到根据条件删除索引的对象
		/*
		 * 在这里我们选择删除掉名称中带有lucene关键字的所有的索引
		 */
		Term term = new Term("bookName", "lucene");
		iw.deleteDocuments(term);
		
		iw.close();
	}
	
	
	/**
	 * 更新索引
	 * 当更新索引的时候,将会先查找这个索引是否存在,如果存在这个索引,那么将会执行更新,如果不存在这个索引,那么就会执行修改
	 * @throws IOException 
	 */
	@Test
	public void Test_6() throws IOException {
		Analyzer analyzer = new IKAnalyzer();
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		
		IndexWriter writer = new IndexWriter(dic,config);
		
		//得到一个文档对象
		Document doc = new Document();
		
		doc.add(new TextField("id","9527",Store.YES));
		doc.add(new TextField("name","mybatis and spring and struts2",Store.YES));
		
		Term term = new Term("name","springmvc");
		
		writer.updateDocument(term,doc);
		
		writer.close();
	}
	
	/**
	 * 封装一个查询的方法
	 * @throws IOException 
	 */
	
	
	public void seacher(Query query) throws IOException {
		// 打印一下查询的语法
		System.out.println("语法:" + query);
		// 建立Query查询对象
		/*
		 * 第一个参数是需要使用的域 第二个参数是需要使用的分析器
		 */
		// 使用查询转换对象获取一个真正的查询对象,有点类似于将一个sql语句解析成为一个sql语句的解析对象

		// 建立索引库的位置对象(需要知道这些索引存放在哪里
		Directory directory = FSDirectory.open(new File(INDEX_PATH));

		// 将索引读取到内存中,需要将索引先读取打内存中才能进行查找,硬盘很慢
		IndexReader reader = DirectoryReader.open(directory);

		// 进行索引的搜索,我们需要创建一个索引的搜索对象
		IndexSearcher searcher = new IndexSearcher(reader);

		// TopDocs表示结果集??
		/*
		 * 第一个参数表示查询对象,第二个参数表示需要几条结果,比如说查询到了10条结果,但是我们只是需要1条结果,这时我们就可以填写1,就只会获取到1条结果
		 */
		TopDocs topDocs = searcher.search(query, 10);

		System.out.println("总记录数量" + topDocs.totalHits);
		System.out.println("实际获取到的数量:" + topDocs.scoreDocs.length);

		// 获取的所有的结果
		ScoreDoc[] result = topDocs.scoreDocs;

		for (ScoreDoc scoreDoc : result) {
			System.out.println("----------------------");

			// 获取文档的id
			int doc = scoreDoc.doc;

			// 获取文档的分值
			float score = scoreDoc.score;

			System.out.println("当前文档的id:" + doc + "当前文档的分值:" + score);

			// 获取真正的文档
			Document document = searcher.doc(doc);

			System.out.println("图书Id:" + document.get("bookId"));
			System.out.println("图书名称:" + document.get("bookName"));
			System.out.println("图书价格:" + document.get("bookPrice"));
			System.out.println("图书图片:" + document.get("bookPic"));
			System.out.println("图书描述" + document.get("bookDesc"));
		}

		reader.close();
	}
	
	/**
	 * 测试使用TermQuery进行查询
	 * @throws IOException 
	 */
	@Test
	public void Test_7() throws IOException {
		
		
		//我们给定的条件是bookName中包含java的图书
		//Term term = new Term("bookName","java");
//		TermQuery query = new TermQuery(term);
		
		//使用数值条件查询,将会查询某个域中值为某个区域内的值
		//两个布尔值参数的意思分别代表是否包含最小值和是否包含最大值,这将会体现在查询语句上面,包含将会是"["而不包含将会是"{"
//		NumericRangeQuery query = NumericRangeQuery.newDoubleRange("bookPrice", 80d, 100d, false, true);
		
		//使用BooleanQuery进行条件的组合
		//如果我们需要使用booleanQuery来进行条件组合查询,那么我们需要先创建其他的条件
		//在这里我们是查询名称中包含lucene,并且价格为80-100的图书
		
		TermQuery query1 = new TermQuery(new Term("bookName","lucene"));
		NumericRangeQuery<Double> query2 = NumericRangeQuery.newDoubleRange("bookPrice", 80d, 100d, false, true);
		
		BooleanQuery query = new BooleanQuery();
		//下面开始添加条件
		query.add(query1, Occur.SHOULD);
		query.add(query2, Occur.MUST);
		
		
		
		//得到查询的对象
		
		//根据这个termQuery对象来进行查询
		seacher(query);
		
	}
	
	
	/**
	 * 我们想要可以手动的设置搜索结果,那么我们就可以通过设置权重值来影响搜索的结果
	 * 在这里我们先编写一个方法来修改原有的数据
	 * @throws IOException 
	 */
	@Test
	public void Test_beforeUpdate() throws IOException {
		Analyzer analyzer = new IKAnalyzer();
		
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		
		IndexWriter writer = new IndexWriter(dic,config);
		
		//得到一个文档对象
		Document doc = new Document();
		
		doc.add(new StringField("bookId", "6",Store.YES));
		
		///在我们不设置分值的情况下,将会出现3个结果,每个结果的分值都是1.09,现在我们对于这条搜索结果设置分值为100f,这个值默认是1
		TextField field = new TextField("bookName", "java测试lucene图书",Store.YES);
		field.setBoost(10000f);
		
		doc.add(field);
		doc.add(new StoredField("bookPic", "6.png"));
		doc.add(new DoubleField("bookPrice", 88d,Store.YES));
		doc.add(new TextField("bookDesc", "图书信息",Store.NO));
		
		Term term = new Term("bookName","java");
		
		writer.updateDocument(term,doc);
		
		writer.close();
	}
	
	
	/**
	 * 再次获取数据并且对于这些数据创建索引
	 * @throws Exception 
	 */
	@Test
	public void Test_8() throws Exception {
		//得到原始数据
		List<Book> list = mapper.findAll();
		
		//转换成文档对象
		List<Document> documents = list.stream()
			.map(this::BookToDocument)
			.collect(Collectors.toList());
		
		//先得到分词器对象
		Analyzer analyzer = new IKAnalyzer();
		
		//得到配置对象
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer);
		
		//得到目录对象
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		//得到写入对象
		IndexWriter iw = new IndexWriter(dic,iwc);
		
		//添加需要写入的对象
		iw.addDocuments(documents);
		iw.close();
	}
	
	
	/**
	 * 根据查询表达式进行查询
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void Test_9() throws IOException, ParseException {
		Analyzer analyzer = new IKAnalyzer();
		
		//打开目录
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		
		//将这个目录中的索引数据读取到内存中
		IndexReader reader = DirectoryReader.open(dic);
		
		//创建query转换对象
		QueryParser qp = new QueryParser("bookName", analyzer);
		
		//根据这个对象创建出查询对象,这里是查询bookName域中包含java的所有的图书
		Query query1 = qp.parse("bookName:java");
		
		//查询图书价格为80-100的图书,我们需要使用NumericQuery
		Query query2 = NumericRangeQuery.newDoubleRange("bookPrice", 80d, 100d, true, true);
		
		//多重条件查询
		BooleanQuery query = new BooleanQuery();
		query.add(query1, Occur.MUST);
		query.add(query2, Occur.MUST);
		
		//创建搜索对象
		IndexSearcher searcher = new IndexSearcher(reader);
		
		//根据查询语句搜索出结果,在这里我们只需要查询出10条结果
		TopDocs results = searcher.search(query, 10);
		
		//得到总共查询到的结果数量
		int totalHits = results.totalHits;
		
		//得到查询到的最大分值
		float maxScore = results.getMaxScore();
		
		//得到所有的查询结果
		ScoreDoc[] result = results.scoreDocs;
		
		//遍历查询结果
		for (ScoreDoc scoreDoc : result) {
			System.err.println("查询到的id"+scoreDoc.doc);
			System.err.println("查询到的分值"+scoreDoc.score);
			
			//输出查询到的内容
			Book book = DocumentToBook(searcher.doc(scoreDoc.doc));
			System.err.println(book);
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
	
	
	private static final Version VERSION ;
	private static final Analyzer ANALYZER;
	
	static {
		VERSION = Version.LUCENE_4_10_3;
		ANALYZER = new IKAnalyzer();
	}
	/**
	 * 删除和修改索引
	 * @throws Throwable 
	 */
	@Test
	public void Test_10() throws Throwable {
		//先要将索引的数据读取
		Directory dic = FSDirectory.open(new File(INDEX_PATH));
		
		//创建写入对象
		IndexWriterConfig iwc = new IndexWriterConfig(VERSION, ANALYZER);
		
		IndexWriter iw = new IndexWriter(dic,iwc);
		
		
		
		Book book = mapper.findAll().stream().findAny().get();
		book.setPrice(10000f);
		Document doc = BookToDocument(book);
		Term term = new Term("bookId",book.getId()+"");
		//根据条件来执行修改,如果含有这个对象,那么就会执行修改,如果不存在这个对象,那么就会执行写入(其实我自己一直都是写入,从来没有修改过)
//		iw.updateDocument(term,doc );
		
		//下面执行删除
//		iw.deleteDocuments(term);
		iw.updateDocument(term, doc);//修改记录会将原有的那条记录删除???
		iw.close();
	}
	
	
	/**
	 * 在查询的时候高亮显示,我们需要使用到lucene的另一个组件包highlighter
	 * @throws Exception 
	 */
	@Test
	public void Test_11() throws Exception {
		//先随意得到一些查询的结果
		Query query = parser.parse("bookName:java");
		
		//建立评分对象
		QueryScorer scorer = new QueryScorer(query);
		
		//由于我们需要对于查询的结果进行输出,那么我们就需要需要一个输出片段对象
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		
		/*
		 * 如果需要进行自定义的html,则需要在这里进行一个格式化语句
		 * 有两个参数,第一个参数是标签头,第二个参数是标签尾
		 */
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
		
		//建立高亮显示组件对象,用于真正的高亮显示输出
		Highlighter lighter = new Highlighter(formatter,scorer);
		
		//设置高亮输出片段对象...这NM什么鬼东西
		lighter.setTextFragmenter(fragmenter);
		
		
		
		TopDocs topDocs = searcher.search(query, 10);
		
		//得到查询的结果
		ScoreDoc[] results = topDocs.scoreDocs;
		
		//实现图书名称的高亮显示
		for (ScoreDoc scoreDoc : results) {
			Document doc = searcher.doc(scoreDoc.doc);
			String bookName = doc.get("bookName");
			TokenStream tokenStream = TokenSources.getTokenStream(doc, "bookName",analyzer);
			//我们只需要对于图书名称进行高亮显示
			/*
			 * 将会使用Hightlighter对象来完成高亮显示
			 */
			bookName = lighter.getBestFragment(tokenStream, bookName);
			//将更改之后的数据封装到book实体类中
			Book book = DocumentToBook(doc);
			book.setBookname(bookName);
			System.err.println(book);
		}
		
		
		//遍历查询到的结果
		
	}
	
}

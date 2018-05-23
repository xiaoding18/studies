package study_jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class Test_Jsoup {

	// 获取html文件中某一个元素节点的值
	@Test
	public void Question_1() throws IOException {
		
		
		
		
	}

	// 测试翻译
	
	public void TestTranslate() throws IOException {
//		String url = Translate.Tran();
//		System.out.println(url);
		// Document doc1 = Jsoup.connect("https://www.baidu.com/").get();

		Document doc = Jsoup.connect("http://example.com").data("query", "Java").userAgent("Mozilla")
				.cookie("auth", "token").timeout(1000).post();
		System.out.println(doc);
	}

	// 测试访问网页
	public void TestConnection() throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		String str = "class";
		String url = Translate.Tran(str);
		HttpClient client = new HttpClient();
		// 设置代理服务器地址和端口

		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		// 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
		HttpMethod method = new GetMethod(url);
		// 使用POST方法
		// HttpMethod method = new PostMethod("http://java.sun.com");
		client.executeMethod(method);

		// 打印服务器返回的状态
		System.out.println(method.getStatusLine());
		// 打印返回的信息
		System.out.println(method.getResponseBodyAsString());
		// 释放连接
		method.releaseConnection();
	}
}

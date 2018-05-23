package study_XML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Study_XML {

	
	//测试XML的读取操作
	//源文件取本包的文件，简化操作
	@Test
	public void Study_XML_Reader() throws Exception {
		//读取book.xml文件中的内容并且显示
		SAXReader read = new SAXReader();
		Document document = read.read("bin/study_XML/book.xml");
		//拿到根节点
		Element root = document.getRootElement();
		//拿到第二个书的节点
		Element element = (Element) root.elements().get(1);
		addAttribute(element);
		updateAttribute(element);
		removeAttribute(element);
	}
	//拿到根节点下所有的节点,使用elements方法
	public void getAllElement(Element root) {
		List<Element> elements = root.elements();
		for (Element element : elements) {
			System.out.println(element.getName());
		}
	}
	
	//拿到一个指定的元素，如果这个元素存在多个，则拿到第一个
	public void getFirstElement(Element root) {
		Element first = root.element("书");
		System.out.println(first);
	}
	
	//拿到一个指定的元素集合
	public void getElements(Element root) {
		List<Element> elements = root.elements("书");
		for (Element element : elements) {
			System.out.println(element);
		}
	}
	
	//应该能拿到一个元素的属性
	public void getAttribute(Element element) {
		Attribute ISBN = element.attribute("ISBN");
		Attribute attribute = element.attribute("出版社");
		System.out.println(ISBN.getValue());
		System.out.println(attribute.getValue());
	}
	
	//获取一个元素的所有的属性的值，并且将其打印在控制台
	public void getAllAttribute(Element element) {
		List<Attribute> attributes = element.attributes();
		for (Attribute attribute : attributes) {
			System.out.println(attribute.getValue());
		}
	}
	
	//根据这个元素的属性名拿到这个元素的值
	public void getAttributeValue(Element element) {
		System.out.println(element.attributeValue("ISBN"));
	}
	
	//获取一个元素之内的文本对象
	public void getTextByElement(Element element) {
		System.out.println(element.getText());
	}
	
	
	//根据子元素的名称获取子元素之内的文本
	public void getTextByChildElement(Element element) {
		System.out.println(element.elementText("作者"));
	}
	
	//使用漂亮的写入
	public void testWriter(Document document) throws Exception, FileNotFoundException {
		OutputFormat of = OutputFormat.createPrettyPrint();
		XMLWriter write = new XMLWriter(new FileOutputStream("src/study_XML/contact.xml"));
		write.write(document);
		write.close();
	}
	
	//使用紧凑的写入
	public void testWriter2(Document document)throws Exception {
		OutputFormat of = OutputFormat.createCompactFormat();
		XMLWriter write = new XMLWriter(new FileOutputStream("src/study_XML/contact.xml"),of);
		write.write(document);
		write.close();
	}
	
	//给元素添加一个属性
	public void addAttribute(Element element) {
		System.out.println("给元素添加一个属性");
		element.addAttribute("name", "吴京");
		System.out.println(element);
	}
	//修改元素的属性
	public void updateAttribute(Element element) {
		System.out.println("给元素修改一个属性");
		element.addAttribute("name2", "吴京2");
		System.out.println(element);
	}
	//给元素删除一个属性
	public void removeAttribute(Element element) {
		System.out.println("给元素删除一个属性");
		Attribute attribute = element.attribute("name2");
		element.remove(attribute);
		System.out.println(element);
	}
	
	
	
	
}

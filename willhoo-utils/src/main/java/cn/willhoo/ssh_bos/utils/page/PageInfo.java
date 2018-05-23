package cn.willhoo.ssh_bos.utils.page;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**   
* @Description 
* @author xiaoding
* @date 2018年4月21日 下午8:52:06   
*/
@XmlRootElement(name="page")
/*@XmlSeeAlso(value={
		Area.class,
		Courier.class,
		FixedArea.class,
		Standard.class,
		SubArea.class,
		Order.class,
		Promotion.class,
		WayBill.class,
		WorkBill.class
})*/
public class PageInfo<T>{
	
	
	private List<T> content = new ArrayList<>();
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;
	@Override
	public String toString() {
		return "PageInfo [content=" + content + ", number=" + number + ", numberOfElements=" + numberOfElements
				+ ", size=" + size + ", totalElements=" + totalElements + ", totalPages=" + totalPages + "]";
	}
	public PageInfo(List<T> content, int number, int numberOfElements, int size, long totalElements, int totalPages) {
		super();
		this.content = content;
		this.number = number;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}
	public PageInfo() {
		super();
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	

}

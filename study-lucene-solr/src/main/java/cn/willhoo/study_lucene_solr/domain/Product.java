package cn.willhoo.study_lucene_solr.domain;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

/**   
* @Description 
* @author xiaoding
* @date 2018年5月16日 下午8:30:09   
*/
public class Product {

	@Field
	private Integer id;
	@Field("product_name")
	private String name;
	@Field("product_catalog")
	private Integer catelog;
	@Field("product_catalog_name")
	private String catalogName;
	@Field("product_price")
	private Double price;
	
	private Integer number;
	
	@Field("product_description")
	private String description;
	@Field("product_picture")
	private String picture;
	
	private Date releaseTime;

	public Product() {
		//TODO
		super();
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", catelog=" + catelog + ", catalogName=" + catalogName
				+ ", price=" + price + ", number=" + number + ", description=" + description + ", picture=" + picture
				+ ", releaseTime=" + releaseTime + "]";
	}


	public Product(Integer id, String name, Integer catelog, String catalogName, Double price, Integer number,
			String description, String picture, Date releaseTime) {
		super();
		this.id = id;
		this.name = name;
		this.catelog = catelog;
		this.catalogName = catalogName;
		this.price = price;
		this.number = number;
		this.description = description;
		this.picture = picture;
		this.releaseTime = releaseTime;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCatelog() {
		return catelog;
	}

	public void setCatelog(Integer catelog) {
		this.catelog = catelog;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
}
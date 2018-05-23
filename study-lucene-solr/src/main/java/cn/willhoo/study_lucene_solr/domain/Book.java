package cn.willhoo.study_lucene_solr.domain;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

@Data
public class Book implements Serializable {
	
	@Field
    private String id;
	@Field
    private String bookname;
	@Field
    private Float price;
	@Field
    private String pic;
	@Field
    private String bookdesc;

}
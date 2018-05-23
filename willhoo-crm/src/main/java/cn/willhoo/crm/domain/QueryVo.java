package cn.willhoo.crm.domain;
/**
 * 用于封装页面传递的参数
 * @author lenovo
 *
 */
public class QueryVo {

	//分页参数
	private Integer page = 1;//当前页码
	private Integer rows = 10;//页面大小
	
	private Integer start;//每次查询的起始行号
	
	//接收客户查询条件
	private String custName;//客户名称
	private String custSource;//客户来源
	private String custIndustry;//客户行业
	private String custLevel;//客户级别
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustSource() {
		return custSource;
	}
	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}
	public String getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
	public String getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
}

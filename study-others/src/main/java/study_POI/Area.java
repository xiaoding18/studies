package study_POI;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
public class Area implements java.io.Serializable {

	// Fields

	private Long id;
	private String areacode;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String citycode;
	private String shortcode;

	// Constructors

	/** default constructor */
	public Area() {
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", areacode=" + areacode + ", province=" + province + ", city=" + city + ", district="
				+ district + ", postcode=" + postcode + ", citycode=" + citycode + ", shortcode=" + shortcode + "]";
	}

	/** full constructor */
	public Area(String areacode, String province, String city, String district,
			String postcode, String citycode, String shortcode) {
		this.areacode = areacode;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.citycode = citycode;
		this.shortcode = shortcode;
	}

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getShortcode() {
		return this.shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}


}
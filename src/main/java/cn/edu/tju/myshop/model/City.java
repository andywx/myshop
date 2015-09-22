package cn.edu.tju.myshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id")
	private String cityId;

	private String name;

	//bi-directional many-to-one association to Province
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Province province;

	//bi-directional many-to-one association to County
	@OneToMany(mappedBy="city")
	private List<County> counties;

	public City() {
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return this.province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<County> getCounties() {
		return this.counties;
	}

	public void setCounties(List<County> counties) {
		this.counties = counties;
	}

	public County addCounty(County county) {
		getCounties().add(county);
		county.setCity(this);

		return county;
	}

	public County removeCounty(County county) {
		getCounties().remove(county);
		county.setCity(null);

		return county;
	}

}
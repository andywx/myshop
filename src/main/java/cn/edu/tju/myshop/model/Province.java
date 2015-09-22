package cn.edu.tju.myshop.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="province_id")
	private String provinceId;

	private String name;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="province")
	private List<City> cities;

	public Province() {
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setProvince(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setProvince(null);

		return city;
	}

}
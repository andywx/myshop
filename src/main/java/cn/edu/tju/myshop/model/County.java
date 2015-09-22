package cn.edu.tju.myshop.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the county database table.
 * 
 */
@Entity
@NamedQuery(name="County.findAll", query="SELECT c FROM County c")
public class County implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="county_id")
	private String countyId;

	private String name;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="parent_id")
	private City city;

	public County() {
	}

	public String getCountyId() {
		return this.countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
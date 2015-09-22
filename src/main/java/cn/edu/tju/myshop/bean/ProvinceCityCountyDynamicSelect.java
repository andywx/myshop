package cn.edu.tju.myshop.bean;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import cn.edu.tju.myshop.data.CityRepository;
import cn.edu.tju.myshop.data.CountyRepository;
import cn.edu.tju.myshop.model.City;
import cn.edu.tju.myshop.model.County;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class ProvinceCityCountyDynamicSelect implements Serializable {

	private static final long serialVersionUID = 1L;

	private String provinceName;
	private String cityName;
	
    private List<City> cityList;
    private List<County> countyList;
	
	@Inject
	private CityRepository cityRepository;
	
	@Inject
	private CountyRepository countyRepository;
	
	public ProvinceCityCountyDynamicSelect() {
		// TODO Auto-generated constructor stub
	}
	
    public List<City> getCityList() {
		return cityList;
	}
    
    public List<County> getCountyList() {
		return countyList;
	}
	
    public void provinceValueChanged(ValueChangeEvent event) {
    	if (null != event.getNewValue()) {
    		provinceName = (String) event.getNewValue();
    		cityList = cityRepository.findByProvinceName(provinceName);
    		if (null != countyList) {
    			countyList.clear();
    		}
    	}
    }
    
    public void cityValueChanged(ValueChangeEvent event) {
    	if (null != event.getNewValue()) {
    		cityName = (String) event.getNewValue();
    		countyList = countyRepository.findByProvinceAndCityName(provinceName, cityName);
    	}
    }

}

package cn.edu.tju.myshop.bean;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import cn.edu.tju.myshop.data.CustomerRepository;
import cn.edu.tju.myshop.model.Customer;

import java.io.Serializable;
import java.security.Principal;

@ManagedBean
@SessionScoped
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	
	@Inject
	private CustomerRepository customerRepository; 
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer getUser() {
		if (customer == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if (principal != null) {
				customer = customerRepository.findByUsername(principal.getName());
			}
		}
		return customer;
	}

}

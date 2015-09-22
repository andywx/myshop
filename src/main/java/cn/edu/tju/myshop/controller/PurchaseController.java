package cn.edu.tju.myshop.controller;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import cn.edu.tju.myshop.bean.ShoppingCart;
import cn.edu.tju.myshop.data.ProductRepository;
import cn.edu.tju.myshop.model.Product;

@ManagedBean
@RequestScoped
public class PurchaseController {
	
    @Inject
    private ProductRepository dao;
    
    @Inject
    private FacesContext facesContext;
    
    @ManagedProperty("#{shoppingCart}") 
    private ShoppingCart shoppingCart;

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void purchase() {
		
		Map<String, String> map = facesContext.getExternalContext().getRequestParameterMap(); 
		String idStr = (String) map.get("pid"); 
		
		Integer id = new Integer((byte)0);
		try {
			id = Integer.valueOf(idStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Product product = dao.findById(id.intValue());
		
		shoppingCart.addProduct(product);
		
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Added to Your Shopping Cart!");
        facesContext.addMessage(null, m);
	}

}

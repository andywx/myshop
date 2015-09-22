package cn.edu.tju.myshop.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.cdi.push.Push;

import cn.edu.tju.myshop.bean.FileUploadBean;
import cn.edu.tju.myshop.model.Product;
import cn.edu.tju.myshop.service.ProductAdmin;

@Model
public class ProductAdminController implements Serializable {

	public static final String PUSH_CDI_TOPIC = "pushCdi";
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext facesContext;
    
    @Inject
    private ProductAdmin productAdmin;

	@Inject
    private Logger log;
	
    @Inject
    @Push(topic = PUSH_CDI_TOPIC)
    Event<String> pushEvent;
    
    private Product newProduct;
    private Product product;

	@Produces
    @Named
    public Product getNewProduct() {
        return newProduct;
    }
    
    @PostConstruct
    public void initNewProduct() {
    	newProduct = new Product();
    	product = new Product();
    }
    
    @Produces
    @Named
    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
	public void add() throws Exception {
		try {
	        log.info("Adding " + newProduct.getName());
	        
	        FileUploadBean fileBean = (FileUploadBean) FacesContext.getCurrentInstance()
	        		.getExternalContext().getSessionMap().get("fileUploadBean");
	        if (fileBean.getSize() > 0) {
	        	newProduct.setImage(fileBean.getFiles().get(0).getName());
	        } else {
	        	newProduct.setImage("null.jpg");
	        }
	        
	        productAdmin.add(newProduct);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "Added successful");
            facesContext.addMessage(null, m);
            initNewProduct();
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("products.xhtml");
            
		} catch (Exception e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Added unsuccessful");
            facesContext.addMessage(null, m);	
            e.printStackTrace();
		}
        
	}
	
	public void delete() {
		Map<String, String> map = facesContext.getExternalContext().getRequestParameterMap(); 
		String idStr = (String) map.get("pid");
		
		Integer id = new Integer((byte)0);
		try {
			id = Integer.valueOf(idStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		productAdmin.delete(id);
		
		pushEvent.fire(String.format("Product deleted: (id: %d)", id));
	}

	public void update() {
		try {
	        log.info("Updating " + product.getName());
	        
	        FileUploadBean fileBean = (FileUploadBean) FacesContext.getCurrentInstance()
	        		.getExternalContext().getSessionMap().get("fileUploadBean");
	        if (fileBean.getSize() > 0) {
	        	product.setImage(fileBean.getFiles().get(0).getName());
	        }
	        
	        productAdmin.update(product);

	        pushEvent.fire(String.format("Product updated: (id: %d)", product.getId()));

		} catch (Exception e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Updated unsuccessful");
            facesContext.addMessage(null, m);	
            e.printStackTrace();
		}
	}
}

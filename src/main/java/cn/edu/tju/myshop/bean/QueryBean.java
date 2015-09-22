package cn.edu.tju.myshop.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Event;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.richfaces.cdi.push.Push;

import cn.edu.tju.myshop.data.ProductRepository;
import cn.edu.tju.myshop.model.Product;

@ManagedBean
@ViewScoped
public class QueryBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PUSH_CDI_TOPIC = "pushCdi";
	
    @Inject
    private ProductRepository productRepository;
    
    @Inject
    @Push(topic = PUSH_CDI_TOPIC)
    Event<String> pushEvent;
    
	private String keyword;

	private List<Product> productResults;

	public QueryBean() {
    }
	
    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<Product> getProductResults() {
		return productResults;
	}
	
	public void queryProducts() {
		
		productResults = productRepository.search(keyword);
		
		pushEvent.fire(String.format("Query completed: (number of results: %d)", productResults.size()));
	
	}
	
}
